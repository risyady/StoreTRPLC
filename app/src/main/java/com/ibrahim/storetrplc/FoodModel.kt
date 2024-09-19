package com.ibrahim.storetrplc

class FoodModel {
    var food_image:Int = 0
    var food_name:String? = null
    var food_desc:String? = null
    var food_rate:String? = null
    var food_price:String? = null

    // Getter
    fun getFoodImages():Int {
        return food_image
    }

    fun getFoodNames():String {
        return food_name.toString()
    }

    fun getFoodDescs():String {
        return food_desc.toString()
    }

    fun getFoodRates():String {
        return food_rate.toString()
    }

    fun getFoodPrices():String {
        return food_price.toString()
    }
    // Getter

    // Setter
    fun setFoodImages(food_image:Int) {
        this.food_image = food_image
    }

    fun setFoodNames(food_name:String) {
        this.food_name = food_name
    }

    fun setFoodDescs(food_desc:String) {
        this.food_desc = food_desc
    }

    fun setFoodRates(food_rate:String) {
        this.food_rate = food_rate
    }

    fun setFoodPrices(food_price:String) {
        this.food_price = food_price
    }
    // Setter
}