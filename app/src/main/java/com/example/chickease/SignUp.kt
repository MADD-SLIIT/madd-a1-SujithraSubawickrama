package com.example.chickease

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chickease.data_class.User
import com.example.chickease.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding activity to root
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()
        //firebase Database Reference
        databaseRef = FirebaseDatabase.getInstance().reference

        binding.SignUpBtn.setOnClickListener {
            val name = binding.edtSignupUsername.text.toString()
            val email = binding.edtSignupEmail.text.toString()
            val password = binding.edtSignupPassword.text.toString()
            val confirm_password = binding.edtSignupReConfirmpassword.text.toString()
            val phone = binding.edtSignupPhoneNo.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirm_password.isNotEmpty()) {
                signUpUser(name, email, password, confirm_password, phone)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

        }

        binding.directSignInPg.setOnClickListener {
            directTosignin()
        }

    }

    private fun signUpUser(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val uid = firebaseAuth.currentUser?.uid
                    if (uid != null) {
                        addUserToDatabase(name, email, uid, password, phone)
                    }
                    Toast.makeText(this, "SignUp Success", Toast.LENGTH_SHORT).show()
                    signupsuccess()
                } else {
                    Toast.makeText(
                        this,
                        "Signup Failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }

    //function to add user to database
    private fun addUserToDatabase(
        name: String,
        email: String,
        uid: String,
        password: String,
        phone: String
    ) {
        val user = User(name, email, uid, password, phone)
        databaseRef.child("Users").child(uid).setValue(user)
    }

    private fun directTosignin() {
        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
        finish()
    }

    private fun signupsuccess() {
        val intent = Intent(this, SignUpSuccess::class.java)
        startActivity(intent)
        finish()
    }
}