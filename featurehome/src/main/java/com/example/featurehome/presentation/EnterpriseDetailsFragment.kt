package com.example.featurehome.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.core.constants.Constants
import com.example.core.extensions.createLoadingDialog
import com.example.core.extensions.downloadImage
import com.example.datasource.model.enterprise.Enterprise
import com.example.featurehome.databinding.FragmentEnterpriseDetailsBinding


class EnterpriseDetailsFragment : Fragment() {
    private lateinit var binding: FragmentEnterpriseDetailsBinding
    private val loadingDialog by lazy { activity?.createLoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentEnterpriseDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //activity?.actionBar(binding.enterpriseDetailsToolBar)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog?.show()
        val enterprise: Enterprise = retrieverEnterprise()
        setupToolBar(enterprise.enterpriseName)
        showEnterpriseDetails(enterprise)
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
        activity?.actionBar?.setDisplayShowTitleEnabled(true)
        activity?.actionBar?.title = enterpriseName
    }

    private fun retrieverEnterprise(): Enterprise {
        //return intent.getSerializableExtra(Constants.ENTERPRISE_DETAILS_KEY) as Enterprise
    }
}