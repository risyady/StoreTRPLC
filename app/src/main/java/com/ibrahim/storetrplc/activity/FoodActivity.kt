package com.ibrahim.storetrplc.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.ibrahim.storetrplc.FoodAdapter
import com.ibrahim.storetrplc.FoodModel
import com.ibrahim.storetrplc.R

class FoodActivity : AppCompatActivity() {

    /**
     * Cara deklarasi variabel
     *
     * Pertama
     * lateinit var ListFood:ListView? = null
     *
     * Kedua (Di dalam method onCreate)
     * val foodList = findViewById<ListView>(R.id.listMakanan)
     *
     * Ketiga
     * (Di luar method onCreate)
     *      private var listFood:ListView? = null
     * (Di dalam method onCreate)
     *      listFood = findViewById(R.id.listMakanan) as ListView
     */

    private var listFood:ListView? = null
    private var foodAdapter: FoodAdapter? = null
    private var foodArrayList:ArrayList<FoodModel>? = null

    private val foodImageList = intArrayOf(
        R.drawable.bakso, R.drawable.gudeg, R.drawable.lumpia,
        R.drawable.nasi_goreng, R.drawable.nasi_liwet, R.drawable.pempek, R.drawable.rawon,
        R.drawable.rendang, R.drawable.sate, R.drawable.tengkleng
    )

    private val foodNameList = arrayOf("Bakso", "Gudeg", "Lumpia", "Nasi Goreng", "Nasi Liwet",
        "Pempek", "Rawon", "Rendang", "Sate", "Tengkleng")

    private val foodPriceList = arrayOf("Rp. 12000", "Rp. 15000", "Rp. 8000", "Rp. 10000",
        "Rp. 13000", "Rp. 7000", "Rp. 14000", "Rp. 15000", "Rp. 12000", "Rp. 11000")

    private val foodDescList = arrayOf(
        "Bakso adalah makanan yang terbuat dari daging, sangat enak, dan ramah di kantong masyarakat",
        "Gudeg adalah makanan khas Yogyakarta yang terbuat dari nangka muda, dimasak dengan santan hingga memiliki rasa manis dan lezat",
        "Lumpia adalah makanan ringan yang terdiri dari kulit renyah yang berisi sayuran, daging, atau udang, sering disajikan dengan saus manis atau pedas",
        "Nasi Goreng adalah hidangan nasi yang digoreng dengan berbagai bumbu, sayuran, telur, dan daging, menciptakan rasa yang gurih dan nikmat",
        "Nasi Liwet adalah nasi gurih yang dimasak dengan santan, biasanya disajikan dengan ikan asin, sambal, dan sayuran, khas dari Solo",
        "Pempek adalah makanan khas Palembang yang terbuat dari ikan dan tepung sagu, disajikan dengan kuah cuka yang asam pedas, membuat rasanya sangat menggugah selera",
        "Rawon adalah sup daging khas Jawa Timur yang berkuah hitam karena menggunakan bumbu kluwak, memberikan rasa yang kaya dan gurih",
        "Rendang adalah hidangan daging yang dimasak dengan santan dan rempah-rempah khas, terkenal karena proses memasaknya yang lama dan menghasilkan rasa pedas gurih",
        "Sate adalah makanan berupa potongan daging kecil yang ditusuk dan dibakar, disajikan dengan bumbu kacang atau kecap manis, memberi cita rasa khas Indonesia",
        "Tengkleng adalah masakan tulang kambing yang dimasak dengan bumbu khas, sering kali disantap bersama nasi, menciptakan rasa gurih dan lezat"
    )

    private val foodRateList = arrayOf("4,9", "4,5", "4,0", "4,3", "4,1", "4,2", "3,9", "4,6", "4,9", "4,0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        listFood = findViewById(R.id.listMakanan) as ListView

        // Log.e("success", foodArrayList!!.size.toString())
        foodArrayList = dataFoodList()

        foodAdapter = FoodAdapter(this@FoodActivity, foodArrayList!!)
        listFood!!.adapter = foodAdapter
    }

    private fun dataFoodList(): ArrayList<FoodModel>? {
        val list = ArrayList<FoodModel>()
        for (i in 0..9) {
            val food = FoodModel()
            food.setFoodImages(foodImageList[i])
            food.setFoodNames(foodNameList[i])
            food.setFoodDescs(foodDescList[i])
            food.setFoodRates(foodRateList[i])
            food.setFoodPrices((foodPriceList[i]))
            list.add(food)
        }
        return list
    }
}