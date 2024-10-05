package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chickease.adapter.FlockAdapter
import com.example.chickease.data_class.Flock
import com.google.firebase.firestore.FirebaseFirestore

class flockViewsList : AppCompatActivity() {

    lateinit var bcktoview : ImageButton

    private lateinit var recyclerView: RecyclerView
    private lateinit var flockAdapter: FlockAdapter
    private lateinit var firestore: FirebaseFirestore
    private var flockList = mutableListOf<Flock>()

    private lateinit var edtbtn : Button
    private lateinit var dltbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.flock_views_list)

        bcktoview = findViewById(R.id.bckToview)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from Firestore
        fetchFlockData()

        // Set up the adapter
        flockAdapter = FlockAdapter(flockList) { selectedFlock ->
            // Handle flock item click, for example, open FlockEditActivity
            val intent = Intent(this, FlockEdit::class.java)
            intent.putExtra("flockId", selectedFlock.flockNo) // Pass flockNo or flockId
            startActivity(intent)
        }
        recyclerView.adapter = flockAdapter

        bcktoview.setOnClickListener {
            val intent = Intent(this,records::class.java)
            startActivity(intent)
        }

    }

    private fun fetchFlockData() {
        firestore.collection("flocks")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val flock = document.toObject(Flock::class.java)
                    flockList.add(flock)
                }
                flockAdapter.notifyDataSetChanged() // Update RecyclerView when data changes
            }
            .addOnFailureListener { e ->
                // Handle error
            }
    }
}