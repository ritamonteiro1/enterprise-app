package com.example.featurehome.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.core.extensions.createLoadingDialog
import com.example.datasource.model.enterprise.Enterprise
import com.example.featurehome.databinding.FragmentEnterpriseDetailsBinding


class EnterpriseDetailsFragment : Fragment() {
    private lateinit var resultViewBinding: FragmentEnterpriseDetailsBinding
    private val loadingDialog by lazy { activity?.createLoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultViewBinding = FragmentEnterpriseDetailsBinding.inflate(layoutInflater)
        loadingDialog?.show()
        val enterprise: Enterprise = retrieverEnterprise()
        setupToolBar(enterprise.enterpriseName)
        showEnterpriseDetails(enterprise)
    }

    private fun showEnterpriseDetails(enterprise: Enterprise) {
        loadingDialog.dismiss()
        resultViewBinding.enterpriseDetailsImageView.downloadImage(
            Constants.BASE_IMAGE_URL + enterprise.photo
        )
        resultViewBinding.enterpriseDetailsTextView.text = enterprise.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolBar(enterpriseName: String) {
        setSupportActionBar(resultViewBinding.enterpriseDetailsToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = enterpriseName
    }

    private fun retrieverEnterprise(): Enterprise {
        return intent.getSerializableExtra(Constants.ENTERPRISE_DETAILS) as Enterprise
    }
}