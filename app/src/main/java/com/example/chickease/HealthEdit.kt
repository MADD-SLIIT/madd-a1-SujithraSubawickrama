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

class HealthEdit : AppCompatActivity() {

    lateinit var bckTohome_btn: ImageButton

    lateinit var edtveccflockNo: EditText
    lateinit var edtveccdate: EditText
    lateinit var edtveccname: EditText
    lateinit var edtveccdesc: EditText
    lateinit var edtveccnxtdate: EditText

    lateinit var btnEditvecc: Button

    private lateinit var firestore: FirebaseFirestore
    private lateinit var documentReference: DocumentReference

    private var veccineId: String = "EtSahQEIuAing79Zc3ya"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health_edit)

        bckTohome_btn = findViewById(R.id.bckTovecchome)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Get references to input fields
        edtveccflockNo = findViewById(R.id.edtVaccflocEdt)
        edtveccdate = findViewById(R.id.edtVaccdateEdt)
        edtveccname = findViewById(R.id.edtVaccnameEdt)
        edtveccdesc = findViewById(R.id.edtVaccdescEdt)
        edtveccnxtdate = findViewById(R.id.edtVaccNxtdateEdt)

        // Button to edit flock data
        btnEditvecc = findViewById(R.id.vecc_edt_btn)

        // Get document reference to the flock
        documentReference = firestore.collection("vaccine").document(veccineId)

        // Call function to retrieve and display data
        retrieveVeccData(
            edtveccflockNo,
            edtveccdate,
            edtveccname,
            edtveccdesc,
            edtveccnxtdate
        )

        btnEditvecc.setOnClickListener {
            val updatedveccflocNo = edtveccflockNo.text.toString()
            val updatedveccdate = edtveccdate.text.toString()
            val updatedveccname = edtveccname.text.toString()
            val updatedveccdesc = edtveccdesc.text.toString()
            val updatedveccnxtdate = edtveccnxtdate.text.toString()

            // Update the document with new values
            val updatedFlockData = mapOf(
                "veccflockNo" to updatedveccflocNo,
                "veccineDate" to updatedveccdate,
                "veccineDescription" to updatedveccdesc,
                "veccineName" to updatedveccname,
                "veccineNextDate" to updatedveccnxtdate
            )

            updateVeccineData(updatedFlockData)
        }

        bckTohome_btn.setOnClickListener {
            nav_bk_health_home()
        }

    }

    private fun updateVeccineData(updatedveccineData: Map<String, String>) {
        // Update the flock document with new values
        documentReference.update(updatedveccineData)
            .addOnSuccessListener {
                Toast.makeText(this, "vaccine updated successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error updating vaccine: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun retrieveVeccData(
        edtveccflockNo: EditText?,
        edtveccdate: EditText?,
        edtveccname: EditText?,
        edtveccdesc: EditText?,
        edtveccnxtdate: EditText?
    ) {
        // Retrieve the existing flock data
        documentReference.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    // Populate the fields with the retrieved data
                    if (edtveccflockNo != null) {
                        edtveccflockNo.setText(document.getString("veccflockNo"))
                    }
                    if (edtveccdate != null) {
                        edtveccdate.setText(document.getString("veccineDate"))
                    }
                    if (edtveccname != null) {
                        edtveccname.setText(document.getString("veccineName"))
                    }
                    if (edtveccdesc != null) {
                        edtveccdesc.setText(document.getString("veccineDescription"))
                    }
                    if (edtveccnxtdate != null) {
                        edtveccnxtdate.setText(document.getString("veccineNextDate"))
                    }
                } else {
                    Toast.makeText(this, "No such document", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to load vaccine: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun nav_bk_health_home() {
        val intent = Intent(this, HealthAdd::class.java)
        startActivity(intent)
    }
}