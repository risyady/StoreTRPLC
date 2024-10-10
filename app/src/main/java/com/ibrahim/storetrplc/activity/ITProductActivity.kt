package com.ibrahim.storetrplc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ibrahim.storetrplc.ProductAdapter
import com.ibrahim.storetrplc.R

class ITProductActivity : AppCompatActivity() {

    private lateinit var fab:FloatingActionButton
    private lateinit var recProduct:RecyclerView
    private lateinit var swipeLayout:SwipeRefreshLayout
    private lateinit var progressBar:ProgressBar
    private lateinit var ProductAdapter:ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itproduct)

        recProduct = findViewById(R.id.recProduct)
        swipeLayout = findViewById(R.id.swipeLayout)
        progressBar = findViewById(R.id.progressBar)
        fab = findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent = Intent(this@ITProductActivity, AddProductActivity::class.java)
            startActivity(intent)
        }

        getProduct()
        swipeLayout.setOnRefreshListener {

        }

    }

    private fun getProduct() {
        // Ganti variabel URLServer dengan memanfaatkan ip addr
        val URLServer = "http://192.168.18.16/server_ecommerce/allproduct.php"
    }
}