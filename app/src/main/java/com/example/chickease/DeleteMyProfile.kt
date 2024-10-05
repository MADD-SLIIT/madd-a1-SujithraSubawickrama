package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteMyProfile : AppCompatActivity() {

    lateinit var BckToProfilebtn: ImageButton
    lateinit var Deletebtn: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_my_profile)

        BckToProfilebtn = findViewById(R.id.bcktoprof)
        Deletebtn = findViewById(R.id.delete_account_btn)

        // Initialize Firebase Auth and Database Reference
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference()

        BckToProfilebtn.setOnClickListener {
            backtoProfile()
        }

        Deletebtn.setOnClickListener {
            deleteAccount()
        }

    }

    //delete account
    private fun deleteAccount() {
        databaseRef.child("Users").child(auth.currentUser?.uid.toString()).removeValue()
        val intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }

    private fun backtoProfile() {
        val intent = Intent(this, MyProfile::class.java)
        startActivity(intent)
    }
}