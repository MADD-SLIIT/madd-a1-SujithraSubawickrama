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

class HealthAdd : AppCompatActivity() {

    lateinit var bckTohome_btn: ImageButton

    // Get references to the input fields
    lateinit var edtVeccflockno: EditText
    lateinit var edtVeccineDate: EditText
    lateinit var edtVeccineName: EditText
    lateinit var edtVeccineDesc: EditText
    lateinit var edtVeccineNextdate: EditText

    lateinit var btnAddVeccine: Button
    lateinit var btnEditVeccine: Button

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health_add)

        bckTohome_btn = findViewById(R.id.bckTovecchome)

        // Initialize Firebase Firestore
        firestore = FirebaseFirestore.getInstance()

        // Get references to the input fields
        edtVeccflockno = findViewById(R.id.edtVaccfloc)
        edtVeccineDate = findViewById(R.id.edtVaccdate)
        edtVeccineName = findViewById(R.id.edtVaccname)
        edtVeccineDesc = findViewById(R.id.edtVaccdesc)
        edtVeccineNextdate = findViewById(R.id.edtVaccNxtdate)


        // Button for adding veccine
        btnAddVeccine = findViewById(R.id.vecc_add_btn)

        // Button for editing veccine
        btnEditVeccine = findViewById(R.id.vecc_edit_btn)

        btnEditVeccine.setOnClickListener {
            val intent = Intent(this, HealthEdit::class.java)
            startActivity(intent)
        }

        btnAddVeccine.setOnClickListener {
            // Collect data from the input fields
            val veccflockNo = edtVeccflockno.text.toString()
            val veccdate = edtVeccineDate.text.toString()
            val veccname = edtVeccineName.text.toString()
            val veccdesc = edtVeccineDesc.text.toString()
            val veccnxtdate = edtVeccineNextdate.text.toString()

            // Create a new flock data entry
            val flockData = HashMap<String, Any>()
            flockData["veccflockNo"] = veccflockNo
            flockData["veccineDate"] = veccdate
            flockData["veccineName"] = veccname
            flockData["veccineDescription"] = veccdesc
            flockData["veccineNextDate"] = veccnxtdate

            firestore.collection("vaccine")
                .add(flockData)
                .addOnSuccessListener {
                    Toast.makeText(this, "vaccine added successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding vaccine: ${e.message}", Toast.LENGTH_SHORT)
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