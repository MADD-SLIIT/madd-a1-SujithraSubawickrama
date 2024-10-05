package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class FeedAdd : AppCompatActivity() {

    lateinit var bckTofeedhome_btn: ImageButton

    // Get references to the input fields
    lateinit var edtflockNo : EditText
    lateinit var edtfeedDate : EditText
    lateinit var edtfeedyTime : EditText
    lateinit var edtfeedType : EditText
    lateinit var edtfeedQuantity : EditText

    lateinit var addfeedBtn : Button
    //lateinit var showfeed : Button

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_feed_add)

        bckTofeedhome_btn = findViewById(R.id.bckTofeedHome)
        //showfeed = findViewById(R.id.feed_show_btn)

    /*    showfeed.setOnClickListener {
            val intent = Intent(this,FeedAdd::class.java)
            startActivity(intent)
        }*/

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        // Get references to the input fields
        edtflockNo = findViewById(R.id.edtFeedfloc)
        edtfeedDate = findViewById(R.id.edtFeeddate)
        edtfeedyTime = findViewById(R.id.edtFeedtime)
        edtfeedType = findViewById(R.id.edtFeedtype)
        edtfeedQuantity = findViewById(R.id.edtFeedquantity)

        addfeedBtn = findViewById(R.id.feed_add_btn)


        //add data in to firestore
        addfeedBtn.setOnClickListener {

            // Collect data from the input fields
            val feedFlockNo = edtflockNo.text.toString()
            val feedDate = edtfeedDate.text.toString()
            val feedTime = edtfeedyTime.text.toString()
            val feedType = edtfeedType.text.toString()
            val feedQuantity = edtfeedQuantity.text.toString()

            //Create a new flock data entry
            val FeedData = HashMap<String , Any>()
            FeedData["feedFlockNo"] = feedFlockNo
            FeedData["feedDate"] = feedDate
            FeedData["feedTime"] = feedTime
            FeedData["feedType"] = feedType
            FeedData["feedQuantity"] = feedQuantity

            firestore.collection("feeds")
                .add(FeedData)
                .addOnSuccessListener {
                    Toast.makeText(this, "feeds added successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding feeds: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bckTofeedhome_btn.setOnClickListener {
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