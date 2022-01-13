package com.example.driversapptest.data.model

import com.google.gson.annotations.SerializedName

class OrdersListResponse {
    @SerializedName("ResultCode")
    var resultCode: String? = null

    @SerializedName("ResultString")
    var message: String? = null
}

