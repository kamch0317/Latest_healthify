package com.example.healthify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthify.databinding.FirstpageBinding
import android.content.Intent

class FirstPage : AppCompatActivity() {
    private lateinit var binding: FirstpageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.signupButton.setOnClickListener { signup() }
        binding.loginButton.setOnClickListener { login() }


    }

    private fun login() {
        val context = binding.loginButton.context
        val intent = Intent(context, Login::class.java)
        startActivity(intent)
    }

    private fun signup() {
        val context = binding.signupButton.context
        val intent = Intent(context, Signup::class.java)
        startActivity(intent)
    }
}