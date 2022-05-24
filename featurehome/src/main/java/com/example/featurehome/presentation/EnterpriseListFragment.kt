package com.example.featurehome.presentation

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.constants.Constants
import com.example.core.extensions.showErrorDialog
import com.example.core.model.EmptyEnterpriseListException
import com.example.core.model.NetworkErrorException
import com.example.datasource.model.enterprise.Enterprise
import com.example.featurehome.R
import com.example.featurehome.databinding.FragmentEnterpriseListBinding
import com.example.navigation.UserTokensNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterpriseListFragment : Fragment() {
    private val viewModel: EnterpriseListViewModel by viewModel()
    private var _binding: FragmentEnterpriseListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterpriseListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun getEnterpriseList(newText: String) {
        val userTokens = recoverUserTokens()
        viewModel.getEnterpriseList(
            newText,
            userTokens?.accessToken.orEmpty(),
            userTokens?.client.orEmpty(),
            userTokens?.uid.orEmpty()
        )
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
                    requireContext().showErrorDialog(errorMessage)
                }
            }
        }
    }

    private fun setupEnterpriseListAdapter(enterpriseList: List<Enterprise>): EnterpriseListAdapter {
        return EnterpriseListAdapter(enterpriseList) { enterprise ->
            val action =
                EnterpriseListFragmentDirections.actionEnterpriseListToEnterpriseDetails(enterprise)
            findNavController().navigate(action)
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
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.enterpriseListToolBar)
            activity?.actionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun recoverUserTokens(): UserTokensNavigation? {
        return requireActivity().intent.getParcelableExtra(Constants.USER_TOKENS_KEY)
    }
}