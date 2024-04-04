package com.chunkingz.mdpquiz

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        // Find views by ID
        val detailProductImage: ImageView = findViewById(R.id.detailProductImage)
        val detailProductName: TextView = findViewById(R.id.detailProductName)
        val detailProductDescription: TextView = findViewById(R.id.detailProductDescription)
        val detailProductCost: TextView = findViewById(R.id.detailProductCost)
        val homeBtn: Button = findViewById(R.id.homeBtn)

        // Retrieve data from Intent
        val productName = intent.getStringExtra("productName")
        val productDescription = intent.getStringExtra("productDescription")
        val productCost = intent.getDoubleExtra("productCost", 0.0)
        val productImageUrl = intent.getStringExtra("productImageUrl")

        // Set data to views
        productName?.let { detailProductName.text = it }
        productDescription?.let { detailProductDescription.text = it }
        detailProductCost.text = getString(R.string.product_cost, productCost)
        productImageUrl?.let {
            Picasso.get().load(it).placeholder(R.drawable.img).into(detailProductImage)
        }

        homeBtn.setOnClickListener { finish() }
    }
}
