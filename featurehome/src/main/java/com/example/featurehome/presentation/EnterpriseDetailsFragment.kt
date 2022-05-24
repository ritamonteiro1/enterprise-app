package com.example.featurehome.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.core.constants.Constants
import com.example.core.extensions.createLoadingDialog
import com.example.core.extensions.downloadImage
import com.example.datasource.model.enterprise.Enterprise
import com.example.featurehome.databinding.FragmentEnterpriseDetailsBinding


class EnterpriseDetailsFragment : Fragment() {
    private var _binding: FragmentEnterpriseDetailsBinding? = null
    private val binding get() = _binding!!
    private val loadingDialog by lazy { activity?.createLoadingDialog() }
    private val navArgs: EnterpriseDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterpriseDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog?.show()
        setupToolBar(navArgs.enterprise.enterpriseName)
        showEnterpriseDetails(navArgs.enterprise)
    }

    private fun showEnterpriseDetails(enterprise: Enterprise) {
        loadingDialog?.dismiss()
        binding.enterpriseDetailsImageView.downloadImage(
            Constants.BASE_IMAGE_URL + enterprise.photo
        )
        binding.enterpriseDetailsTextView.text = enterprise.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolBar(enterpriseName: String) {
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.enterpriseDetailsToolbar)
            actionBar?.setDisplayShowTitleEnabled(true)
            actionBar?.title = enterpriseName
        }
    }
}