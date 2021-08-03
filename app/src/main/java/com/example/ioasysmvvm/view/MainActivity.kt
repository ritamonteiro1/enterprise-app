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
import com.example.ioasysmvvm.model.click.listener.OnItemClickListener
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
import com.example.ioasysmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val loadingDialog: Dialog by lazy { createLoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        viewModel.getEnterpriseList(accessToken, client, uid, newText)
    }

    private fun setupObservers() {
        viewModel.enterpriseList.observe(this, {
            val enterpriseList: List<Enterprise> = it
            val enterpriseListAdapter = setupEnterpriseListAdapter(enterpriseList)
            setupRecyclerView(enterpriseListAdapter)
        })
    }

    private fun setupEnterpriseListAdapter(enterpriseList: List<Enterprise>): EnterpriseListAdapter {
        return EnterpriseListAdapter(
            enterpriseList,
            object : OnItemClickListener<Enterprise> {
                override fun onClick(item: Enterprise) {
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    intent.putExtra(Constants.ENTERPRISE_DETAILS, item)
                    startActivity(intent)
                }
            })
    }

    private fun setupRecyclerView(enterpriseListAdapter: EnterpriseListAdapter) {
        binding.mainRecyclerView.adapter = enterpriseListAdapter
        val layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding.mainRecyclerView.layoutManager = layoutManager
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.mainToolBar)
        supportActionBar?.setLogo(R.drawable.img_logo_nav)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayUseLogoEnabled(true)
    }
}