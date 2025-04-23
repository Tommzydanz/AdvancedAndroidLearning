package com.example.learnnavigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.navHostFragmentContainerView)

        // determines whether the navigation action should
        // be handled by the nav controller or by the parent class if navigateUp returns true then navigation flow will work as defined
        // but in case it returns false means due to certain reason it fails to navigate from one screen
        // to another screen then in that case it falls back to the default behavior provided by the parent class

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}