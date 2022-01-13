package com.example.driversapptest.data.model

import com.google.gson.annotations.SerializedName

class GoodsResponse {
    @SerializedName("Plan")
    var planOrder: Int? = null

    @SerializedName("NumberСlient")
    var numberСlient: String? = null

    @SerializedName("OrderNumber")
    var orderNumber: String? = null
}