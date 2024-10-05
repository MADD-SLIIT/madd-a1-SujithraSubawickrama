package com.example.chickease.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.chickease.FeedAdd
import com.example.chickease.FlockAdd
import com.example.chickease.HealthAdd
import com.example.chickease.R
import com.example.chickease.records

class HomeFragment : Fragment() {

    lateinit var flockBtn : Button
    lateinit var vaccinationBtn : Button
    lateinit var Feedbtn :Button
    lateinit var recordss : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        flockBtn = view.findViewById(R.id.flock_btn)
        vaccinationBtn = view.findViewById(R.id.vecinate_btn)
        Feedbtn = view.findViewById(R.id.feeds_btn)
        recordss = view.findViewById(R.id.record_btn)

        flockBtn.setOnClickListener {
            nav_flockAdd()
        }

        vaccinationBtn.setOnClickListener {
            nav_vccineAdd()
        }

        Feedbtn.setOnClickListener {
            nav_feedAdd()
        }

        recordss.setOnClickListener {
            nav_records()
        }

        return view
    }

    private fun nav_records() {
        val intent = Intent(activity, records::class.java)
        startActivity(intent)
    }

    private fun nav_feedAdd() {
        val intent = Intent(activity, FeedAdd::class.java)
        startActivity(intent)
    }

    private fun nav_vccineAdd() {
        val intent = Intent(activity, HealthAdd::class.java)
        startActivity(intent)
    }

    private fun nav_flockAdd() {
        val intent = Intent(activity, FlockAdd::class.java)
        startActivity(intent)
    }


}