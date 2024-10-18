package com.ibrahim.storetrplc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ibrahim.storetrplc.R
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class AddProductActivity : AppCompatActivity() {

    private lateinit var Back:Button
    private lateinit var Add:Button
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
        Back = findViewById(R.id.btnBack)
        Add = findViewById(R.id.btnAdd)

        Back.setOnClickListener {
            val intent = Intent(this@AddProductActivity, ITProductActivity::class.java)
            startActivity(intent)
        }

        Add.setOnClickListener {
            val namePd = nameProduct.text.toString().trim()
            val promoPd = promoProduct.text.toString().trim()
            val pricePd = priceProduct.text.toString().trim()
            val descriptionPd = descProduct.text.toString().trim()
            val imagePd = imageProduct.text.toString().trim()

            if (TextUtils.isEmpty(namePd)) {
                nameProduct.setError("Please enter product name")
            } else if (TextUtils.isEmpty(pricePd)) {
                priceProduct.setError("Please enter product price")
            } else if (TextUtils.isEmpty(promoPd)) {
                promoProduct.setError("Please enter product promo")
            } else if (TextUtils.isEmpty(descriptionPd)) {
                descProduct.setError("Please enter product description")
            } else if (TextUtils.isEmpty(imagePd)) {
                imageProduct.setError("Please enter product image URL")
            } else {
                addDataProduct(namePd, pricePd, promoPd, descriptionPd, imagePd)
            }
        }
    }

    private fun addDataProduct(namePd:String, pricePd:String, promoPd:String, descPd:String, imagePd:String) {
        val URL_ADD = "http://192.168.79.17/server_ecommerce/addproduct.php"
        val queue = Volley.newRequestQueue(this@AddProductActivity)
        val request:StringRequest = object:StringRequest(Method.POST, URL_ADD,
            object: Response.Listener<String>{
                override fun onResponse(response: String?) {
                    try {
                        val jsonobject = JSONObject(response)
                        Toast.makeText(
                            this@AddProductActivity,
                            "Add New Product Successfully.",
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    // Untuk menghapus datanya lagi setelah berhasil
                    nameProduct.setText("")
                    priceProduct.setText("")
                    promoProduct.setText("")
                    descProduct.setText("")
                    imageProduct.setText("")

                }
                }, Response.ErrorListener {
                    error -> Toast.makeText(this@AddProductActivity, "Add New Product Failed to Add.", Toast.LENGTH_LONG).show()
                }){
                    override fun getBodyContentType(): String {
                        return "application/x-www-form-urlencoded;charset=UTF-8"
                    }

                    override fun getParams(): Map<String, String>? {
                        val params : MutableMap<String, String> = HashMap()
                        params.put("name", namePd)
                        params.put("price", pricePd)
                        params.put("promo", promoPd)
                        params.put("description", descPd)
                        params.put("images", imagePd)
                        return params
                    }
        }
        queue.add(request)
    }
}