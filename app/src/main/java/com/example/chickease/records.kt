package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class records : AppCompatActivity() {

    lateinit var bckTohomefromreord : ImageButton
    lateinit var viewflockrecords : Button
    lateinit var viewvaccinerecords : Button
    lateinit var viewfeedrecords : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_records)

        bckTohomefromreord = findViewById(R.id.bckTorecHome)
        viewflockrecords = findViewById(R.id.flock_view_btn)
        viewvaccinerecords = findViewById(R.id.veccine_view_btn)
        viewfeedrecords = findViewById(R.id.feed_view_btn)

        viewflockrecords.setOnClickListener {
            val intent = Intent(this,flockViewsList::class.java)
            startActivity(intent)
        }

        viewvaccinerecords.setOnClickListener {
            val intent = Intent(this,HealthViewList::class.java)
            startActivity(intent)
        }

        viewfeedrecords.setOnClickListener {
            val intent = Intent(this,Feedviewlist::class.java)
            startActivity(intent)
        }


        bckTohomefromreord.setOnClickListener {
            onBackPressed()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragmentManager = supportFragmentManager
                if (fragmentManager.backStackEntryCount > 0) {
                    fragmentManager.popBackStack() // Pop the top fragment from the back stack
                } else {
                    finish()  // No fragments left, finish the activity
                }
            }
        })
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack() // Pop the top fragment from the back stack
        } else {
            super.onBackPressed() // Default back behavior
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}