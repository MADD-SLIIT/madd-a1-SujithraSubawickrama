package com.example.chickease

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chickease.databinding.ActivitySignInBinding
import com.example.chickease.databinding.ActivitySignUpSuccessBinding

class SignUpSuccess : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_success)

        //binding activity to root
        binding = ActivitySignUpSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.donebtn.setOnClickListener {
            directToHome()
        }
    }

    //Navigate to Home screen
    private fun directToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}