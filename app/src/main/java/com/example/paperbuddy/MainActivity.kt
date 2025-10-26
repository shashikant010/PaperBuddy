package com.example.paperbuddy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Install the splash screen
        installSplashScreen()

        super.onCreate(savedInstanceState)

        // 2. Enable edge-to-edge
        enableEdgeToEdge()

        // 3. Set the layout
        setContentView(R.layout.activity_main)

        // 4. Setup Navigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Optional: Setup the Toolbar to show fragment labels
        // You will need to add a <com.google.android.material.appbar.MaterialToolbar>
        // to your activity_main.xml to use this.
        // setupActionBarWithNavController(navController)
    }
}