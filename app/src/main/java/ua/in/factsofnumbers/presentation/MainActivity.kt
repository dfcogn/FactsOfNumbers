package ua.`in`.factsofnumbers.presentation

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import ua.`in`.factsofnumbers.R
import ua.`in`.factsofnumbers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        val navController = findNavController(R.id.fragment_container_view)
        val appBarConfig = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfig)
    }
}