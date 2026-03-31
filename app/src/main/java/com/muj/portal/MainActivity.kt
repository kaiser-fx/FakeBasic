package com.muj.portal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muj.portal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAppGrid()
        updateWelcomeMessage()
    }

    override fun onResume() {
        super.onResume()
        updateWelcomeMessage()
    }

    private fun updateWelcomeMessage() {
        val sharedPref = getSharedPreferences("MUJ_PREFS", Context.MODE_PRIVATE)
        val name = sharedPref.getString("USER_NAME", "KUSHAGRA TARUN")
        binding.tvUserName.text = name?.uppercase()
    }

    private fun setupAppGrid() {
        val apps = listOf(
            AppItem(R.drawable.ic_ticket, "Tickets") { },
            AppItem(R.drawable.ic_announcement, "Announcements") { },
            AppItem(R.drawable.ic_mess, "Mess Manager") {
                startActivity(Intent(this, MessManagerActivity::class.java))
            },
            AppItem(R.drawable.ic_amenity, "Amenity") {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        )
        binding.recyclerApps.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerApps.adapter = AppGridAdapter(apps)
    }
}

data class AppItem(val iconRes: Int, val label: String, val onClick: () -> Unit)

class AppGridAdapter(private val items: List<AppItem>) :
    RecyclerView.Adapter<AppGridAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.imgAppIcon)
        val label: TextView = view.findViewById(R.id.tvAppLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.iconRes)
        holder.label.text = item.label
        holder.itemView.setOnClickListener { item.onClick() }
    }

    override fun getItemCount() = items.size
}
