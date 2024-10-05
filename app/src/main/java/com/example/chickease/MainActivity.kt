package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var logobtn : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        logobtn = findViewById(R.id.main_Logo_btn)

        logobtn.setOnClickListener {
            directTogetStarted()
        }

    }

    private fun directTogetStarted() {
        val intent = Intent(this,GetStartedPage::class.java)
        startActivity(intent)
        finish()
    }
}