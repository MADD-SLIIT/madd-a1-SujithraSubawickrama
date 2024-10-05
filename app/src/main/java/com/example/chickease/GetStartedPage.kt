package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetStartedPage : AppCompatActivity() {

    lateinit var getStartedbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_started_page)

        getStartedbtn = findViewById(R.id.getstartBtn)

        getStartedbtn.setOnClickListener {
            directToSignUp()
        }

    }

    private fun directToSignUp() {
        val intent = Intent(this,SignUp::class.java)
        startActivity(intent)
        finish()
    }
}