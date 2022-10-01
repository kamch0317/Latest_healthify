package com.example.healthify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.healthify.databinding.LoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class Login : AppCompatActivity() {
    private lateinit var binding: LoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        checkField()
        binding.loginButton2.setOnClickListener { login() }


    }

    private fun login() {
        val db = DataBHelper(this)
        val name = binding.loginUsername.text
        val pass = binding.loginPassword.text

        val userName = name.toString()
        val password = pass.toString()
        val check = db.checkpass(userName, password)
        val checkuser = db.checkusername(userName)


        if (userName.isNullOrBlank()) {
            binding.username.error = "PLease fill in the field"


        } else if (binding.loginPassword.text.isNullOrBlank()) {
            binding.password.error = "PLease fill in the field"

        }else (
                if (checkuser == true) {
            if (check == true) {
                Toast.makeText(this, "WELCOME BACK", Toast.LENGTH_SHORT).show()
                val context = binding.loginButton2.context
                val intent = Intent(context, Home::class.java)
                intent.putExtra("username", userName)
                startActivity(intent)

            } else
                binding.password.error = "Password Incorrect Please try again"
                binding.username.error = null

        } else
            binding.username.error = "User name do not exist Please try again"
                )


    }

    private fun checkField() {
        binding.loginUsername.doOnTextChanged { text, start, before, count ->
            if (text!!.length > 20) {
                binding.username.error = "Invalid User name, please try again"
            } else if (text.length < 20)
                binding.username.error = null
        }

        binding.loginPassword.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotBlank()) {
                binding.password.error = null
            }
        }

    }
}

