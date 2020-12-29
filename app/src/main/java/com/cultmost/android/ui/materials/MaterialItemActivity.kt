package com.cultmost.android.ui.materials

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cultmost.MyApp
import com.cultmost.android.R
import com.cultmost.android.domain.Item
import com.cultmost.android.domain.Material
import com.cultmost.android.ui.Items.MaterialItemsAdapter
import kotlinx.android.synthetic.main.activity_material_item.*

class MaterialItemActivity : AppCompatActivity() {

    private lateinit var material: Material

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_item)

        material = MyApp.materials[intent.getIntExtra("position", 0)]

        Glide.with(this).load(material.image).centerCrop().into(bgheader)

        setSupportActionBar(toolbar!!)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        collapse_toolbar.title = material.title
        description.text = "${material.description} • ${material.count}"

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = MaterialItemsAdapter(Glide.with(this), material.items).apply {
            setOnItemClickListener(object : MaterialItemsAdapter.OnItemClickListener {
                override fun onClick(position: Int, item: Item) {
                    openUrl(item.url)
                }

            })
        }
    }

    private fun openUrl(url: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(url)
            )
        )
    }
}