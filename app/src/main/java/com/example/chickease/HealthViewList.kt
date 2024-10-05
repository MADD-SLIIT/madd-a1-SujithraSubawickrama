package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chickease.adapter.HealthAdapter
import com.example.chickease.data_class.Health
import com.google.firebase.firestore.FirebaseFirestore

class HealthViewList : AppCompatActivity() {


    lateinit var bcktoview: ImageButton

    private lateinit var recyclerView: RecyclerView
    private lateinit var healthAdapter: HealthAdapter
    private lateinit var firestore: FirebaseFirestore

    private var healthList = mutableListOf<Health>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_health_view_list)

        bcktoview = findViewById(R.id.bckToview)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Set up RecyclerView
        recyclerView = findViewById(R.id.health_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from Firestore
        fetchHealthData()

        healthAdapter = HealthAdapter(healthList) { selectedHealth ->
            // Handle health item click, for example, open HealthEditActivity
            val intent = Intent(this, HealthEdit::class.java)
            intent.putExtra("veccineId", selectedHealth.veccineflockNo) // Pass flockNo or flockId
            startActivity(intent)
        }
        recyclerView.adapter = healthAdapter

        bcktoview.setOnClickListener {
            val intent = Intent(this, records::class.java)
            startActivity(intent)
        }

    }

    private fun fetchHealthData() {
        firestore.collection("vaccine")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val health = document.toObject(Health::class.java)
                    healthList.add(health)
                }
                healthAdapter.notifyDataSetChanged() // Update RecyclerView when data changes
            }
            .addOnFailureListener { e ->
                // Handle error
            }
    }
}