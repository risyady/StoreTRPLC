package com.ibrahim.storetrplc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ibrahim.storetrplc.Product
import com.ibrahim.storetrplc.ProductAdapter
import com.ibrahim.storetrplc.R
import org.json.JSONArray
import org.json.JSONException
import java.text.DecimalFormat

class ITProductActivity : AppCompatActivity() {

    private lateinit var fab:FloatingActionButton
    private lateinit var recProduct:RecyclerView
    private lateinit var swipeLayout:SwipeRefreshLayout
    private lateinit var progressBar:ProgressBar
    private lateinit var productAdapter:ProductAdapter
    private lateinit var productList:ArrayList<Product>
    private lateinit var search:EditText
    private lateinit var order:Button

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

        search = findViewById(R.id.editSearch)

        search.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    getSearchProduct(s.toString())
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        getProduct()
        swipeLayout.setOnRefreshListener {
            getProduct()
            swipeLayout.isRefreshing = false
        }

        order = findViewById(R.id.btnHarga)
        order.setOnClickListener {
            getProductByPrice()
        }
    }

    private fun getProductByPrice() {
        val URL_ORDER = "http://192.168.18.16/server_ecommerce/listproduct.php"
        val ordRequest = StringRequest(Request.Method.GET, URL_ORDER,
            { response ->
                val jsonArray = JSONArray(response)
                val orderProduct = ArrayList<Product>()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    //val pPrice = "Harga: Rp " + DecimalFormat("#,###").format(jsonObject.getString("price").toInt())
                    //val pPromo = "Promo: Rp " + DecimalFormat("#,###").format(jsonObject.getString("promo").toInt())
                    val itemProduct = Product(
                        jsonObject.getString("name"),
                        "Harga: Rp " + jsonObject.getString("price"),
                        "Promo\nRp " + jsonObject.getString("promo"),
                        "Diskon\nRp " + getDisc(jsonObject.getString("price").toInt(), jsonObject.getString("promo").toInt()),
                        jsonObject.getString("description"),
                        jsonObject.getString("images")
                    )
                    orderProduct.add(itemProduct)
                }
                productAdapter.productOrders(orderProduct)
            }, {
                    error -> Toast.makeText(this@ITProductActivity, "Product Not Found", Toast.LENGTH_LONG).show()
            })
        Volley.newRequestQueue(this@ITProductActivity).add(ordRequest)
    }

    private fun getSearchProduct(query: String) {
        //val URL_SEARCH = "http://192.168.79.17/server_ecommerce/searchproduct.php?search=$query"
        val URL_SEARCH = "http://192.168.18.16/server_ecommerce/searchproduct.php?search=$query"
        val strRequest = StringRequest(Request.Method.GET, URL_SEARCH,
            { response ->
                val jsonArray = JSONArray(response)
                val filterProduct = ArrayList<Product>()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    //val pPrice = "Harga: Rp " + DecimalFormat("#,###").format(jsonObject.getString("price").toInt())
                    //val pPromo = "Promo: Rp " + DecimalFormat("#,###").format(jsonObject.getString("promo").toInt())
                    val itemProduct = Product(
                        jsonObject.getString("name"),
                        "Harga: Rp " + jsonObject.getString("price"),
                        "Promo\nRp " + jsonObject.getString("promo"),
                        "Diskon\nRp " + getDisc(jsonObject.getString("price").toInt(), jsonObject.getString("promo").toInt()),
                        jsonObject.getString("description"),
                        jsonObject.getString("images")
                    )
                    filterProduct.add(itemProduct)
                }
                productAdapter.productItems(filterProduct)
            }, {
                error -> Toast.makeText(this@ITProductActivity, "Product Not Found", Toast.LENGTH_LONG).show()
            })
        Volley.newRequestQueue(this@ITProductActivity).add(strRequest)
    }

    private fun getProduct() {
        // Ganti variabel URLServer dengan memanfaatkan ip addr
        val URL_SERVER = "http://192.168.18.16/server_ecommerce/allproduct.php"
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
                    val pPromo = "Promo\nRp " + responseObj.getString("promo")
                    val pDescription = responseObj.getString("description")
                    val pImages = responseObj.getString("images")

                    val pDisc = "Diskon\nRp " + getDisc(responseObj.getString("price").toInt(), responseObj.getString("promo").toInt())

                    productList.add(Product(pName, pPrice, pPromo, pDisc, pDescription, pImages))
                    // notifyDataSetChanged digunakan untuk searching data
                    productAdapter.notifyDataSetChanged()
                }
                progressBar.visibility = View.GONE // Hide ProgressBar
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }, {
            error -> Toast.makeText(this@ITProductActivity, "FAILED TO GET DATA FROM SERVER", Toast.LENGTH_LONG).show()
        })
        queue.add(request)
        progressBar.visibility = View.VISIBLE
    }

    private fun getDisc(price: Int, promo: Int): String {
        var disc:Int = 0
        if (promo > 0) {
            disc = price - promo
        }
        return disc.toString()
    }
}