package com.example.ioasysmvvm.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.ioasysmvvm.databinding.ActivityResultBinding
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
import com.example.ioasysmvvm.model.extensions.downloadImage

class ResultActivity : AppCompatActivity() {
    private lateinit var resultViewBinding: ActivityResultBinding
    private val loadingDialog: Dialog by lazy { createLoadingDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultViewBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(resultViewBinding.root)
        loadingDialog.show()
        val enterprise: Enterprise = retrieverEnterprise()
        setupToolBar(enterprise.enterpriseName)
        showEnterpriseDetails(enterprise)
    }

    private fun showEnterpriseDetails(enterprise: Enterprise) {
        loadingDialog.dismiss()
        resultViewBinding.resultEnterpriseImageView.downloadImage(
            Constants.BASE_IMAGE_URL + enterprise.photo
        )
        resultViewBinding.resultDescriptionEnterpriseTextView.text = enterprise.description
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolBar(enterpriseName: String) {
        setSupportActionBar(resultViewBinding.resultToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = enterpriseName
    }

    private fun retrieverEnterprise(): Enterprise {
        return intent.getSerializableExtra(Constants.ENTERPRISE_DETAILS) as Enterprise
    }
}