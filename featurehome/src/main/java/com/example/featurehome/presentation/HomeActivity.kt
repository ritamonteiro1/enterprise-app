package com.example.featurehome.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.core.constants.Constants
import com.example.featurehome.R
import com.example.featurehome.databinding.ActivityHomeBinding
import com.example.navigation.UserTokensNavigation

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeFragmentContainer) as NavHostFragment
    }

    companion object {
        fun launch(context: Context, userTokensNavigation: UserTokensNavigation) {
            context.startActivity(Intent(context, HomeActivity::class.java).apply {
                putExtra(Constants.USER_TOKENS_KEY, userTokensNavigation)
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.homeFragmentContainer)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}