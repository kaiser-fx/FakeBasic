package com.muj.portal

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.muj.portal.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val amenities = arrayOf("Bluedove", "BlueSpring")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, amenities)
        binding.autoCompleteAmenity.setAdapter(adapter)

        // Load existing data
        val sharedPref = getSharedPreferences("MUJ_PREFS", Context.MODE_PRIVATE)
        binding.etName.setText(sharedPref.getString("USER_NAME", ""))
        
        val savedAmenity = sharedPref.getString("SELECTED_AMENITY", "Bluedove")
        binding.autoCompleteAmenity.setText(savedAmenity, false)

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString()
            val amenity = binding.autoCompleteAmenity.text.toString()

            if (name.isEmpty()) {
                binding.etName.error = "Please enter your name"
                return@setOnClickListener
            }

            with(sharedPref.edit()) {
                putString("USER_NAME", name)
                putString("SELECTED_AMENITY", amenity)
                apply()
            }
            finish()
        }

        setupSocialLinks()
    }

    private fun setupSocialLinks() {
        binding.btnInstagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/kushagra.tarun"))
            startActivity(intent)
        }
        binding.btnGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/kaiser-fx"))
            startActivity(intent)
        }
    }
}
