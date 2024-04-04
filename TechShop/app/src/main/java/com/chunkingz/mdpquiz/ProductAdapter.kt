package com.chunkingz.mdpquiz

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val productCost: TextView = itemView.findViewById(R.id.productCost)
        val addToCartBtn: Button = itemView.findViewById(R.id.addToCartBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        Picasso.get()
            .load(product.imageUrl)
            .placeholder(R.drawable.img) // Placeholder image
            .into(holder.productImage)

        holder.productName.text = product.productName
        holder.productDescription.text = product.productDescription
        holder.productCost.text = context.getString(R.string.product_cost, product.cost)

        updateCartButtonText(holder.addToCartBtn, product.isAddedToCart)

        holder.addToCartBtn.setOnClickListener {
            product.isAddedToCart = !product.isAddedToCart // Toggle isAddedToCart
            updateCartButtonText(holder.addToCartBtn, product.isAddedToCart)

        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java).apply {
                putExtra("productName", product.productName)
                putExtra("productDescription", product.productDescription)
                putExtra("productCost", product.cost)
                putExtra("productImageUrl", product.imageUrl)
            }
            context.startActivity(intent)
        }
    }

    private fun updateCartButtonText(button: Button, isAddedToCart: Boolean) {
        if (isAddedToCart) {
            button.text = context.getString(R.string.remove_from_cart)
        } else {
            button.text = context.getString(R.string.add_to_cart)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
