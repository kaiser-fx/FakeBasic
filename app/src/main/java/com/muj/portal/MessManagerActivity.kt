package com.muj.portal

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.muj.portal.databinding.ActivityMessManagerBinding

class MessManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Retrieve the saved Amenity
        val sharedPref = getSharedPreferences("MUJ_PREFS", Context.MODE_PRIVATE)
        val selectedAmenity = sharedPref.getString("SELECTED_AMENITY", "BlueDove") ?: "BlueDove"

        // Update Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Mess Manager"
        }

        // Update the "Allotted Mess" Card UI
        binding.tvAllottedMessName.text = selectedAmenity
        binding.tvMessInitial.text = if (selectedAmenity.isNotEmpty()) selectedAmenity[0].toString() else "M"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
