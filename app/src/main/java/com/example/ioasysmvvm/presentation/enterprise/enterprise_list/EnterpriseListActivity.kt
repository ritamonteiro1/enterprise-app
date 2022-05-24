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
import com.example.ioasysmvvm.domain.exception.EmptyEnterpriseListException
import com.example.ioasysmvvm.domain.exception.NetworkErrorException
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.extensions.showErrorDialog
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
        searchView.queryHint = getString(R.string.enterprise_list_query_hint_menu_search)
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
        viewModel.enterpriseList.observe(this) { enterpriseList ->
            binding.enterpriseListTextView.visibility = View.GONE
            binding.enterpriseListRecyclerView.visibility = View.VISIBLE
            val enterpriseListAdapter = setupEnterpriseListAdapter(enterpriseList)
            setupRecyclerView(enterpriseListAdapter)
        }
        viewModel.loading.observe(this) { isLoading ->
            binding.enterpriseListProgressBar.isVisible = isLoading
        }
        viewModel.error.observe(this) { exception ->
            when (exception) {
                is EmptyEnterpriseListException -> {
                    binding.enterpriseListRecyclerView.visibility = View.GONE
                    binding.enterpriseListTextView.text =
                        getString(R.string.empty_list_error_message)
                    binding.enterpriseListTextView.visibility = View.VISIBLE
                }
                else -> {
                    val errorMessage = when (exception) {
                        is NetworkErrorException -> getString(R.string.error_connection_fail)
                        else -> getString(R.string.occurred_error)
                    }
                    showErrorDialog(errorMessage)
                }
            }
        }
    }

    private fun setupEnterpriseListAdapter(enterpriseList: List<Enterprise>): EnterpriseListAdapter {
        return EnterpriseListAdapter(enterpriseList) { enterprise ->
            val intent =
                Intent(this@EnterpriseListActivity, EnterpriseDetailsActivity::class.java)
            intent.putExtra(Constants.ENTERPRISE_DETAILS, enterprise)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView(enterpriseListAdapter: EnterpriseListAdapter) {
        binding.enterpriseListRecyclerView.adapter = enterpriseListAdapter
        val layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding.enterpriseListRecyclerView.layoutManager = layoutManager
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.enterpriseListToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}