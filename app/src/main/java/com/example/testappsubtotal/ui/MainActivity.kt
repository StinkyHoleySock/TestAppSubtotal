package com.example.testappsubtotal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testappsubtotal.R
import com.example.testappsubtotal.databinding.ActivityMainBinding
import com.example.testappsubtotal.utils.NetworkConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this) { isConnected ->

            if (isConnected) {
                binding.fragmentContainer.visibility = View.VISIBLE
                binding.checkConnectionContainer.visibility = View.GONE
            } else {
                binding.fragmentContainer.visibility = View.GONE
                binding.checkConnectionContainer.visibility = View.VISIBLE
            }

        }
    }
}