package com.example.featurehome.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.featurehome.R
import com.example.featurehome.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeFragment) as NavHostFragment
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.homeFragment)
//        return navController.navigateUp()
//                || super.onSupportNavigateUp()
//    }
}