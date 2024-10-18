package com.ibrahim.storetrplc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private var productList: ArrayList<Product>)
    :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Layout Inflater
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itproduct_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.nameP.text = productList.get(position).name
        holder.descP.text = productList.get(position).description
        holder.priceP.text = productList.get(position).price
        holder.promoP.text = productList.get(position).promo
        holder.discP.text = productList.get(position).discount
        Picasso.get().load(productList.get(position).images).into(holder.imageP)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun productItems(newItems:ArrayList<Product>) {
        productList = newItems
        notifyDataSetChanged()
    }

    fun productOrders(orderProduct: ArrayList<Product>) {
        productList = orderProduct
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameP: TextView = itemView.findViewById(R.id.txtProduct)
        val descP: TextView = itemView.findViewById(R.id.txtDescription)
        val priceP: TextView = itemView.findViewById(R.id.txtPrice)
        val promoP: TextView = itemView.findViewById(R.id.txtPromo)
        val imageP: ImageView = itemView.findViewById(R.id.imageProduct)
        val discP: TextView = itemView.findViewById(R.id.txtDiscount)
    }
}