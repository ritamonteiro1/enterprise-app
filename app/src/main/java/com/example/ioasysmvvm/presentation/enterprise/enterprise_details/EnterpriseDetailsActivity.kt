package com.example.ioasysmvvm.presentation.enterprise.enterprise_details

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ioasysmvvm.constants.Constants
import com.example.ioasysmvvm.databinding.ActivityEnterpriseDetailsBinding
import com.example.ioasysmvvm.domain.model.enterprise.Enterprise
import com.example.ioasysmvvm.extensions.createLoadingDialog
import com.example.ioasysmvvm.extensions.downloadImage

class EnterpriseDetailsActivity : AppCompatActivity() {
    private lateinit var resultViewBinding: ActivityEnterpriseDetailsBinding
    private val loadingDialog: Dialog by lazy { createLoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultViewBinding = ActivityEnterpriseDetailsBinding.inflate(layoutInflater)
        setContentView(resultViewBinding.root)
        loadingDialog.show()
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