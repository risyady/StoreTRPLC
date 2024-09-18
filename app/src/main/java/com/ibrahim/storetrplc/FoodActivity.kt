package com.ibrahim.storetrplc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.Toast

class FoodActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener {

    val imageFood = intArrayOf(R.drawable.bakso, R.drawable.gudeg, R.drawable.lumpia,
        R.drawable.nasi_goreng, R.drawable.nasi_liwet, R.drawable.pempek, R.drawable.rawon,
        R.drawable.rendang, R.drawable.sate, R.drawable.tengkleng)

    val nameFood = arrayOf("Bakso", "Gudeg", "Lumpia", "Nasi Goreng", "Nasi Liwet",
        "Pempek", "Rawon", "Rendang", "Sate", "Tengkleng")

    val priceFood = arrayOf("Rp. 12000", "Rp. 15000", "Rp. 8000", "Rp. 10000",
        "Rp. 13000", "Rp. 7000", "Rp. 14000", "Rp. 15000", "Rp. 12000", "Rp. 11000")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this@FoodActivity, "Tidak ada makanan yang terpilih", Toast.LENGTH_LONG).show()
    }
}