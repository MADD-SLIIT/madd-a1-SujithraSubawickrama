package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FlockEdit : AppCompatActivity() {

    lateinit var bckTohome_btn: ImageButton

    lateinit var edtFlockNo: EditText
    lateinit var edtFlockAge: EditText
    lateinit var edtFlockType: EditText
    lateinit var edtFlockSize: EditText
    lateinit var edtFlockDateOfEstablish: EditText

    lateinit var btnEditFlock: Button

    private lateinit var firestore: FirebaseFirestore
    private lateinit var documentReference: DocumentReference
    private var flockId: String = "PbeDXROuMZ8weqSKJxR8"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flock_edit)

        bckTohome_btn = findViewById(R.id.bckToflockadd)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Get references to input fields
        edtFlockNo = findViewById(R.id.edtPgFlockno)
        edtFlockAge = findViewById(R.id.edtPgFlockage)
        edtFlockType = findViewById(R.id.edtPgFlocktype)
        edtFlockSize = findViewById(R.id.edtPgFlocksize)
        edtFlockDateOfEstablish = findViewById(R.id.edtPgFlockDateOfEstablish)

        // Button to edit flock data
        btnEditFlock = findViewById(R.id.flock_add_btn)

        // Get document reference to the flock
        documentReference = firestore.collection("flocks").document(flockId)

        // Call function to retrieve and display data
        retrieveFlockData(
            edtFlockNo,
            edtFlockAge,
            edtFlockType,
            edtFlockSize,
            edtFlockDateOfEstablish
        )

        btnEditFlock.setOnClickListener {
            val updatedFlockNo = edtFlockNo.text.toString()
            val updatedFlockAge = edtFlockAge.text.toString()
            val updatedFlockType = edtFlockType.text.toString()
            val updatedFlockSize = edtFlockSize.text.toString()
            val updatedDateOfEstablishment = edtFlockDateOfEstablish.text.toString()

            // Update the document with new values
            val updatedFlockData = mapOf(
                "flockNo" to updatedFlockNo,
                "flockAge" to updatedFlockAge,
                "flockType" to updatedFlockType,
                "flockSize" to updatedFlockSize,
                "dateOfEstablishment" to updatedDateOfEstablishment
            )

            updateFlockData(updatedFlockData)
        }

        bckTohome_btn.setOnClickListener {
            nav_bk_flock_home()
        }
    }

    private fun updateFlockData(updatedFlockData: Map<String, String>) {
        // Update the flock document with new values
        documentReference.update(updatedFlockData)
            .addOnSuccessListener {
                Toast.makeText(this, "Flock updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error updating flock: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun retrieveFlockData(
        edtFlockNo: EditText?,
        edtFlockAge: EditText?,
        edtFlockType: EditText?,
        edtFlockSize: EditText?,
        edtFlockDateOfEstablish: EditText?
    ) {
        // Retrieve the existing flock data
        documentReference.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    // Populate the fields with the retrieved data
                    if (edtFlockNo != null) {
                        edtFlockNo.setText(document.getString("flockNo"))
                    }
                    if (edtFlockAge != null) {
                        edtFlockAge.setText(document.getString("flockAge"))
                    }
                    if (edtFlockType != null) {
                        edtFlockType.setText(document.getString("flockType"))
                    }
                    if (edtFlockSize != null) {
                        edtFlockSize.setText(document.getString("flockSize"))
                    }
                    if (edtFlockDateOfEstablish != null) {
                        edtFlockDateOfEstablish.setText(document.getString("dateOfEstablishment"))
                    }
                } else {
                    Toast.makeText(this, "No such document", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load flock: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun nav_bk_flock_home() {
        val intent = Intent(this, FlockAdd::class.java)
        startActivity(intent)
    }
}