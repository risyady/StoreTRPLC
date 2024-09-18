package com.ibrahim.storetrplc

class FoodModel {
    var food_image:Int = 0
    var food_name:String? = null
    var food_price:String? = null

    // Getter
    fun getFoodImages():Int {
        return food_image
    }

    fun getFoodNames():String {
        return food_name.toString()
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

    fun setFoodPrices(food_price:String) {
        this.food_price = food_price
    }
    // Setter
}