package com.example.ioasysmvvm.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.ioasysmvvm.R
import com.example.ioasysmvvm.model.constants.Constants
import com.example.ioasysmvvm.model.extensions.createLoadingDialog
import com.example.ioasysmvvm.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private var mainInformationTextView: TextView? = null
    private var mainToolBar: Toolbar? = null
    private var mainRecyclerView: RecyclerView? = null
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = createLoadingDialog()
        findViewsById()
        setupObservers()
        setupToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.search)
        val searchView: SearchView = menuItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.main_query_hint_menu_search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                queryTextChangeInSearchMenu(newText.orEmpty())
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun queryTextChangeInSearchMenu(newText: String) {
        val accessToken = intent.getStringExtra(Constants.HEADER_ACCESS_TOKEN).orEmpty()
        val uid = intent.getStringExtra(Constants.HEADER_UID).orEmpty()
        val client = intent.getStringExtra(Constants.HEADER_CLIENT).orEmpty()
    }

    private fun setupObservers() {
        mainViewModel.enterpriseList.observe(this, {

        })
    }

    private fun setupToolBar() {
        setSupportActionBar(mainToolBar)
        supportActionBar?.setLogo(R.drawable.img_logo_nav)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayUseLogoEnabled(true)
    }

    private fun findViewsById() {
        mainInformationTextView = findViewById(R.id.mainInformationTextView)
        mainToolBar = findViewById(R.id.mainToolBar)
        mainRecyclerView = findViewById(R.id.mainRecyclerView)
    }
}