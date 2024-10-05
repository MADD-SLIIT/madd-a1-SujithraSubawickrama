package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chickease.adapter.FeedAdapter
import com.example.chickease.adapter.FlockAdapter
import com.example.chickease.data_class.Feed
import com.example.chickease.data_class.Flock
import com.google.firebase.firestore.FirebaseFirestore

class Feedviewlist : AppCompatActivity() {

    lateinit var bcktoview: ImageButton

    private lateinit var recyclerView: RecyclerView
    private lateinit var feedAdapter: FeedAdapter
    private lateinit var firestore: FirebaseFirestore
    private var feedList = mutableListOf<Feed>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_feedviewlist)

        bcktoview = findViewById(R.id.bckToview)

        firestore = FirebaseFirestore.getInstance()

        // Set up RecyclerView
        recyclerView = findViewById(R.id.feed_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch data from Firestore
        fetchFeedData()

        feedAdapter = FeedAdapter(feedList) { selectedFeed ->
            // Handle flock item click, for example, open FlockEditActivity
            val intent = Intent(this, FeedAdd::class.java)
            intent.putExtra("feedId", selectedFeed.feedFlockNo) // Pass flockNo or flockId
            startActivity(intent)
        }
        recyclerView.adapter = feedAdapter

        bcktoview.setOnClickListener {
            val intent = Intent(this, records::class.java)
            startActivity(intent)
        }
    }

    private fun fetchFeedData() {
        firestore.collection("feeds")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val feed = document.toObject(Feed::class.java)
                    feedList.add(feed)
                }
                feedAdapter.notifyDataSetChanged() // Update RecyclerView when data changes
            }
            .addOnFailureListener { e ->
                // Handle error
            }
    }
}