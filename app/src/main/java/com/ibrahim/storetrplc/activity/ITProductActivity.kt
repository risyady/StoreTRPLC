package com.ibrahim.storetrplc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ibrahim.storetrplc.Product
import com.ibrahim.storetrplc.ProductAdapter
import com.ibrahim.storetrplc.R
import java.text.DecimalFormat

class ITProductActivity : AppCompatActivity() {

    private lateinit var fab:FloatingActionButton
    private lateinit var recProduct:RecyclerView
    private lateinit var swipeLayout:SwipeRefreshLayout
    private lateinit var progressBar:ProgressBar
    private lateinit var productAdapter:ProductAdapter
    private lateinit var productList:ArrayList<Product>

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

        // Buat arrayList dan adapter nya
        productList = ArrayList()
        productAdapter = ProductAdapter(productList)
        recProduct.adapter = productAdapter

        getProduct()
        swipeLayout.setOnRefreshListener {
            getProduct()
            swipeLayout.isRefreshing = false
        }
    }

    private fun getProduct() {
        // Ganti variabel URLServer dengan memanfaatkan ip addr
        val URL_SERVER = "http://192.168.89.5/server_ecommerce/allproduct.php"
        val queue = Volley.newRequestQueue(this@ITProductActivity)
        val request = JsonArrayRequest(Request.Method.GET, URL_SERVER, null, {
            response ->
            try {
                productList.clear()
                for (i in 0 until response.length()) {
                    val responseObj = response.getJSONObject(i)
                    val pName = responseObj.getString("name")
                    //val pPrice = "Harga: Rp " + DecimalFormat("#,###").format(responseObj.getString("price").toInt())
                    val pPrice = "Harga: Rp " + responseObj.getString("price")
                    //val pPromo = "Promo: Rp " + DecimalFormat("#,###").format(responseObj.getString("promo").toInt())
                    val pPromo = "Promo: Rp " + responseObj.getString("promo")
                    val pDescription = responseObj.getString("description")
                    val pImages = responseObj.getString("images")

                    productList.add(Product(pName, pPrice, pPromo, pDescription, pImages))
                    productAdapter.notifyDataSetChanged()
                }
                progressBar.visibility = View.GONE // Hide ProgressBar
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }, {
            error -> Toast.makeText(this@ITProductActivity, "FAILED TO GET DATA FROM SERVER", Toast.LENGTH_LONG).show()
            progressBar.visibility = View.GONE // Hide ProgressBar
        })
        queue.add(request)
        progressBar.visibility = View.VISIBLE
    }
}