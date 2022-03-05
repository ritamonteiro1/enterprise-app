package com.example.ioasysmvvm.presentation.enterprise.enterprise_list

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.databinding.ActivityEnterpriseListBinding
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.extensions.showErrorDialog
import com.example.ioasysmvvm.presentation.click_listener.OnItemClickListener
import com.example.ioasysmvvm.presentation.enterprise.enterprise_details.EnterpriseDetailsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterpriseListActivity : AppCompatActivity() {
    private val viewModel: EnterpriseListViewModel by viewModel()
    private lateinit var binding: ActivityEnterpriseListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterpriseListBinding.inflate(layoutInflater)
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
                getEnterpriseList(newText.orEmpty())
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getEnterpriseList(newText: String) {
        val accessToken = intent.getStringExtra(Constants.HEADER_ACCESS_TOKEN).orEmpty()
        val uid = intent.getStringExtra(Constants.HEADER_UID).orEmpty()
        val client = intent.getStringExtra(Constants.HEADER_CLIENT).orEmpty()
        viewModel.getEnterpriseList(newText, accessToken, client, uid)
    }

    private fun setupObservers() {
        viewModel.enterpriseList.observe(this) {
            binding.mainInformationNoResultTextView.visibility = View.GONE
            binding.mainRecyclerView.visibility = View.VISIBLE
            val enterpriseListAdapter = setupEnterpriseListAdapter(it)
            setupRecyclerView(enterpriseListAdapter)
        }
        viewModel.emptyListMessage.observe(this) {
            binding.mainInformationTextView.isVisible = it
        }
        viewModel.loading.observe(this) {
            binding.mainProgressBar.isVisible = it
        }
        viewModel.error.observe(this) {
            showErrorDialog(it.message.orEmpty())
        }
    }

    private fun setupEnterpriseListAdapter(enterpriseList: List<Enterprise>): EnterpriseListAdapter {
        return EnterpriseListAdapter(
            enterpriseList,
            object : OnItemClickListener<Enterprise> {
                override fun onClick(item: Enterprise) {
                    val intent =
                        Intent(this@EnterpriseListActivity, EnterpriseDetailsActivity::class.java)
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
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}