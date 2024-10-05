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

class FlockAdd : AppCompatActivity() {

    lateinit var bckTohome_btn: ImageButton

    // Get references to the input fields
    lateinit var edtFlockNo: EditText
    lateinit var edtFlockAge: EditText
    lateinit var edtFlockType: EditText
    lateinit var edtFlockSize: EditText
    lateinit var edtFlockDateOfEstablish: EditText

    lateinit var btnAddFlock: Button
    lateinit var btnEditFlok: Button

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flock_add)

        bckTohome_btn = findViewById(R.id.bckToflockhome)

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        // Get references to the input fields
        edtFlockNo = findViewById(R.id.edtFlockno)
        edtFlockAge = findViewById(R.id.edtFlockage)
        edtFlockType = findViewById(R.id.edtFlocktype)
        edtFlockSize = findViewById(R.id.edtFlocksize)
        edtFlockDateOfEstablish = findViewById(R.id.edtflockDateOfEstablish)

        // Button for adding flock
        btnAddFlock = findViewById(R.id.flock_add_btn)

        // Button for editing flock
        btnEditFlok = findViewById(R.id.flock_edit_btn)

        btnEditFlok.setOnClickListener {
            val intent = Intent(this, FlockEdit::class.java)
            startActivity(intent)
        }

        btnAddFlock.setOnClickListener {
            // Collect data from the input fields
            val flockNo = edtFlockNo.text.toString()
            val flockAge = edtFlockAge.text.toString()
            val flockType = edtFlockType.text.toString()
            val flockSize = edtFlockSize.text.toString()
            val dateOfEstablishment = edtFlockDateOfEstablish.text.toString()

            // Create a new flock data entry
            val flockData = HashMap<String, Any>()
            flockData["flockNo"] = flockNo
            flockData["flockAge"] = flockAge
            flockData["flockType"] = flockType
            flockData["flockSize"] = flockSize
            flockData["dateOfEstablishment"] = dateOfEstablishment

            firestore.collection("flocks")
                .add(flockData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Flock added successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding flock: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bckTohome_btn.setOnClickListener {
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