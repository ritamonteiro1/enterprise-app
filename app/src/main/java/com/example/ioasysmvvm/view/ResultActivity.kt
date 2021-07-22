package com.example.ioasysmvvm.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.ioasysmvvm.R
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
    }

    private fun findViewsById() {
        resultToolBar = findViewById(R.id.resultToolBar)
        resultEnterpriseImageView = findViewById(R.id.resultEnterpriseImageView)
        resultDescriptionEnterpriseTextView = findViewById(R.id.resultDescriptionEnterpriseTextView)
    }
}