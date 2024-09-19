package com.ibrahim.storetrplc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class FoodAdapter(private val context: Context, private val foodArrayList: ArrayList<FoodModel>):BaseAdapter() {

    override fun getCount(): Int {
        return foodArrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        // Mengambil index
        return 0
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        /**
         * View? itu untuk layout
         *
         * position
         * consortView
         * Parent -> apakah layout tersebut terletak di root 'layout'?
         */
        var convertView = convertView
        val holder:ViewHolder

        if(convertView == null) {
            holder = ViewHolder()
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            convertView = inflater.inflate(R.layout.food_item, null, true)
            holder.imageFood = convertView.findViewById(R.id.foodImage) as ImageView
            holder.nameFood = convertView.findViewById(R.id.foodName) as TextView
            holder.descFood = convertView.findViewById(R.id.foodDesc) as TextView
            holder.rateFood = convertView.findViewById(R.id.foodRate) as TextView
            holder.priceFood = convertView.findViewById(R.id.foodPrice) as TextView

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.imageFood!!.setImageResource(foodArrayList[position].getFoodImages())
        holder.nameFood!!.setText(foodArrayList[position].getFoodNames())
        holder.descFood!!.setText(foodArrayList[position].getFoodDescs())
        holder.rateFood!!.setText(foodArrayList[position].getFoodRates())
        holder.priceFood!!.setText(foodArrayList[position].getFoodPrices())

        return convertView!!
    }

    // inner class, untuk mengambil widget dari layout
    private inner class ViewHolder {
        var imageFood : ImageView? = null
        var nameFood : TextView? = null
        var descFood : TextView? = null
        var rateFood : TextView? = null
        var priceFood : TextView? = null
    }
    // inner class, untuk mengambil widget dari layout

}