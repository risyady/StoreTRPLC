package com.ibrahim.storetrplc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibrahim.storetrplc.R

class ITProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itproduct)

        val URLServer = "http://<change>/ecommerce/dbconnect.php"
    }
}