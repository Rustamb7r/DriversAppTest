package com.example.driversapptest.data.model

import com.example.driversapptest.domain.model.Goods
import com.google.gson.annotations.SerializedName

class GoodsListResponse {
    @SerializedName("ResultCode")
    var result: String? = null

    @SerializedName("ResultString")
    var result2: Boolean? = null

    @SerializedName("data")
    var result3: Array<GoodsResponse>? = null
}

fun GoodsListResponse?.toGoodsList(): List<Goods> {
    return this?.result3?.map {
        Goods(
                planOrder = it.planOrder ?: 0,
                numberСlient = it.numberСlient ?: "",
                orderNumber = it.orderNumber ?: ""
        )
    } ?: emptyList()
}