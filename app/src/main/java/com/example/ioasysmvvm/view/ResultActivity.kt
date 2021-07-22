package com.example.ioasysmvvm.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.domains.enterprise.Enterprise
import com.example.ioasysmvvm.model.extensions.createLoadingDialog

class ResultActivity : AppCompatActivity() {
    private var resultToolBar: Toolbar? = null
    private var resultEnterpriseImageView: ImageView? = null
    private var resultDescriptionEnterpriseTextView: TextView? = null
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        loadingDialog = createLoadingDialog()
        findViewsById()
        val enterprise: Enterprise = retrieverEnterprise()
        setupToolBar(enterprise.enterpriseName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolBar(enterpriseName: String) {
        setSupportActionBar(resultToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = enterpriseName
    }

    private fun retrieverEnterprise(): Enterprise {
        return intent.getSerializableExtra(Constants.ENTERPRISE_DETAILS) as Enterprise
    }

    private fun findViewsById() {
        resultToolBar = findViewById(R.id.resultToolBar)
        resultEnterpriseImageView = findViewById(R.id.resultEnterpriseImageView)
        resultDescriptionEnterpriseTextView = findViewById(R.id.resultDescriptionEnterpriseTextView)
    }
}