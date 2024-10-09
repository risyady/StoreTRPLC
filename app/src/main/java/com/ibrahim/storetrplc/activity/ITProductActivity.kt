package com.ibrahim.storetrplc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibrahim.storetrplc.R

class ITProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itproduct)

        // Ganti variabel URLServer dengan memanfaatkan ip addr
        val URLServer = "http://<Nanti Ganti>/server_ecommerce/allproduct.php"
    }
}