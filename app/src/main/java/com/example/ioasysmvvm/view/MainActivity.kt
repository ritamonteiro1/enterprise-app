package com.example.ioasysmvvm.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.databinding.ActivityMainBinding
import com.example.ioasysmvvm.model.adapter.EnterpriseListAdapter
import com.example.ioasysmvvm.model.click.listener.OnEnterpriseItemClickListener
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
import com.example.ioasysmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private var mainViewBinding: ActivityMainBinding? = null
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewBinding?.root)
        loadingDialog = createLoadingDialog()
        setupObservers()
        setupToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView: SearchView = menuItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.main_query_hint_menu_search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                queryTextChangeInSearchMenu(newText.orEmpty())
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun queryTextChangeInSearchMenu(newText: String) {
        val accessToken = intent.getStringExtra(Constants.HEADER_ACCESS_TOKEN).orEmpty()
        val uid = intent.getStringExtra(Constants.HEADER_UID).orEmpty()
        val client = intent.getStringExtra(Constants.HEADER_CLIENT).orEmpty()
        mainViewModel.getEnterpriseList(accessToken, client, uid, newText)
    }

    private fun setupObservers() {
        mainViewModel.enterpriseList.observe(this, {
            val enterpriseList: List<Enterprise> = it
            val enterpriseListAdapter = setupEnterpriseListAdapter(enterpriseList)
            setupRecyclerView(enterpriseListAdapter)
        })
    }

    private fun setupEnterpriseListAdapter(enterpriseList: List<Enterprise>): EnterpriseListAdapter {
        return EnterpriseListAdapter(
            enterpriseList,
            object : OnEnterpriseItemClickListener {
                override fun onClick(enterprise: Enterprise) {
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra(Constants.ENTERPRISE_DETAILS, enterprise)
                    startActivity(intent)
                }
            })
    }

    private fun setupRecyclerView(enterpriseListAdapter: EnterpriseListAdapter) {
        mainViewBinding?.mainRecyclerView?.adapter = enterpriseListAdapter
        val layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        mainViewBinding?.mainRecyclerView?.layoutManager = layoutManager
    }

    private fun setupToolBar() {
        setSupportActionBar(mainViewBinding?.mainToolBar)
        supportActionBar?.setLogo(R.drawable.img_logo_nav)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayUseLogoEnabled(true)
    }
}