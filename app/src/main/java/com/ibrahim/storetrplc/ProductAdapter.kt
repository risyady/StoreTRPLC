package com.ibrahim.storetrplc

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ProductAdapter(private val productList: ArrayList<Product>)
    :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameP: TextView = itemView.findViewById(R.id.txtProduct)
        val descP: TextView = itemView.findViewById(R.id.txtDescription)
        val priceP: TextView = itemView.findViewById(R.id.txtPrice)
        val promoP: TextView = itemView.findViewById(R.id.txtPromo)
        val imageP: ImageView = itemView.findViewById(R.id.imageProduct)
    }
}