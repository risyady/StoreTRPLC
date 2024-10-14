package com.ibrahim.storetrplc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ibrahim.storetrplc.R
import org.json.JSONException
import org.json.JSONObject

class AddProductActivity : AppCompatActivity() {

    private lateinit var btnSearch:Button
    private lateinit var btnAdd:Button
    private lateinit var nameProduct:EditText
    private lateinit var promoProduct:EditText
    private lateinit var descProduct:EditText
    private lateinit var imageProduct:EditText
    private lateinit var priceProduct:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        nameProduct = findViewById(R.id.editName)
        promoProduct = findViewById(R.id.editPromo)
        priceProduct = findViewById(R.id.editPrice)
        descProduct = findViewById(R.id.editDescription)
        imageProduct = findViewById(R.id.editImage)
        btnSearch = findViewById(R.id.btnSearch)
        btnAdd = findViewById(R.id.btnAdd)
        btnSearch.setOnClickListener {
            val intent = Intent(this@AddProductActivity, ITProductActivity::class.java)
            startActivity(intent)

            btnAdd.setOnClickListener {
                val name = nameProduct.text.toString().trim()
                val promo = promoProduct.text.toString().trim()
                val price = priceProduct.text.toString().trim()
                val description = descProduct.text.toString().trim()
                val image = imageProduct.text.toString().trim()

                if (TextUtils.isEmpty(name)) {
                    nameProduct.setError("Please enter product name")
                } else if (TextUtils.isEmpty(price)) {
                    priceProduct.setError("Please enter product price")
                } else if (TextUtils.isEmpty(promo)) {
                    promoProduct.setError("Please enter product promo")
                } else if (TextUtils.isEmpty(description)) {
                    descProduct.setError("Please enter product description")
                } else if (TextUtils.isEmpty(image)) {
                    imageProduct.setError("Please enter product image URL")
                } else {
                    addDataProduct(name, price, promo, description, image)
                }
            }
        }
    }

    private fun addDataProduct(namePd:String, pricePd:String, promoPd:String, descPd:String, imagePd:String) {
        var URL_ADD = "http://192.168.89.5/server_ecommerce/addproduct.php"
        val queue = Volley.newRequestQueue(this@AddProductActivity)
        val request:StringRequest = object:StringRequest(Method.POST, URL_ADD,
            object: Response.Listener<String>, Response.ErrorListener {
                override fun onResponse(response: String?) {
                    try {
                        val jsonobject = JSONObject(response)
                        Toast.makeText(this@AddProductActivity, "Add New Product Successfully.", Toast.LENGTH_LONG).show()
                    } catch (e:JSONException) {
                        e.printStackTrace()
                    }
                    // Untuk menghapus datanya lagi setelah berhasil
                    nameProduct.setText("")
                    priceProduct.setText("")
                    promoProduct.setText("")
                    descProduct.setText("")
                    imageProduct.setText("")
                },

                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(this@AddProductActivity, "Add New Product Failed to Add.", Toast.LENGTH_LONG).show()
                }

            }
    }
}