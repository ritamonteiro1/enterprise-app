package com.example.featurehome.presentation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.constants.Constants
import com.example.core.extensions.showErrorDialog
import com.example.core.model.EmptyEnterpriseListException
import com.example.core.model.NetworkErrorException
import com.example.datasource.model.enterprise.Enterprise
import com.example.featurehome.R
import com.example.featurehome.databinding.FragmentEnterpriseListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterpriseListFragment : Fragment() {
    private val viewModel: EnterpriseListViewModel by viewModel()
    private lateinit var binding: FragmentEnterpriseListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterpriseListBinding.inflate(layoutInflater)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val menuItem = menu.findItem(R.id.search)
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
    }

    private fun getEnterpriseList(newText: String) {
        val accessToken = activity?.intent?.getStringExtra(Constants.HEADER_ACCESS_TOKEN).orEmpty()
        val uid = activity?.intent?.getStringExtra(Constants.HEADER_UID).orEmpty()
        val client = activity?.intent?.getStringExtra(Constants.HEADER_CLIENT).orEmpty()
        viewModel.getEnterpriseList(newText, accessToken, client, uid)
    }

    private fun setupObservers() {
        viewModel.enterpriseList.observe(viewLifecycleOwner) { enterpriseList ->
            binding.enterpriseListTextView.visibility = View.GONE
            binding.enterpriseListRecyclerView.visibility = View.VISIBLE
            val enterpriseListAdapter = setupEnterpriseListAdapter(enterpriseList)
            setupRecyclerView(enterpriseListAdapter)
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.enterpriseListProgressBar.isVisible = isLoading
        }
        viewModel.error.observe(viewLifecycleOwner) { exception ->
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
                    activity?.showErrorDialog(errorMessage)
                }
            }
        }
    }

    private fun setupEnterpriseListAdapter(enterpriseList: List<Enterprise>): EnterpriseListAdapter {
        return EnterpriseListAdapter(enterpriseList) { enterprise ->
            //val action = EnterpriseListFragment
        }
    }

    private fun setupRecyclerView(enterpriseListAdapter: EnterpriseListAdapter) {
        binding.enterpriseListRecyclerView.adapter = enterpriseListAdapter
        val layoutManager = LinearLayoutManager(
            activity, LinearLayoutManager.VERTICAL, false
        )
        binding.enterpriseListRecyclerView.layoutManager = layoutManager
    }

    private fun setupToolBar() {
        //setSupportActionBar(binding.enterpriseListToolBar)
        activity?.actionBar?.setDisplayShowTitleEnabled(false)
    }
}