package com.chunkingz.mdpquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val products = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        products.add(Product("iPad", "iPad Pro 11-inch", 400.0, "https://i5.walmartimages.com/seo/2022-Apple-MacBook-Pro-Laptop-M2-chip-13-inch-Retina-Display-8GB-RAM-512GB-SSD-Storage-Touch-Bar-Backlit-Keyboard-FaceTime-HD-Camera-Works-iPhone-iPa_59254575-0ad4-4bac-bb19-a98d170b61d2.a845feb86e81f0aae7db0539c8ee3691.jpeg?odnHeight=768&odnWidth=768&odnBg=FFFFFF"))
        products.add(Product("MacBook M3 Pro", "12-core CPU 18-core GPU", 2500.0, "https://helios-i.mashable.com/imagery/reviews/03y8gbj1mqCuexgXxFJ5vyX/hero-image.fill.size_1248x702.v1623391330.jpg"))
        products.add(Product("Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.00, "https://yourimageurl.com/image1.png"))
        products.add(Product("Logitech Keyboard", "Logitech - PRO XTKL LIGHTSPEED Wireless", 199.00,"https://yourimageurl.com/image1.png"))
        products.add(Product("MacBook M3 Max", "14-core CPU30-core GPU", 3499.00,"https://yourimageurl.com/image1.png"))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ProductAdapter(this, products) { product ->
            showProductDetails(product)
        }
        recyclerView.adapter = adapter

        val viewCartBtn = findViewById<Button>(R.id.viewCartBtn)
        viewCartBtn.setOnClickListener {
            showCartItems()
        }
    }

    private fun showProductDetails(product: Product) {
        val intent = Intent(this, ProductAdapter::class.java).apply {
            putExtra("productName", product.productName)
            putExtra("productDescription", product.productDescription)
            putExtra("productCost", product.cost)
            putExtra("productImageUrl", product.imageUrl)
        }
        startActivity(intent)
    }

    private fun showCartItems() {
        val cartItems = products.filter { it.isAddedToCart }
        val cartItemNames = cartItems.joinToString(separator = ", ") { it.productName }
        Toast.makeText(this, "Cart Items: $cartItemNames", Toast.LENGTH_SHORT).show()
    }

}
