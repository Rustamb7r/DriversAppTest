package com.example.driversapptest.data

import com.example.driversapptest.data.model.GoodsListResponse
import com.example.driversapptest.data.model.OrdersListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

const val BASE_URL = "https://gate.vozovoz.xyz/"
//const val BASE_URL = "https://gate.vozovoz.ru/"

interface GoodsService {
    @GET("tsd/GetPrealert")
    fun getPrealertAsync(
            @Query("Date") date: String,
            @Query("terminalCode") terminalCode: String
    ): Deferred<GoodsListResponse?>

    @POST("tsd/setPrealertPrint")
    fun setPrealertPrintAsync(
            @Query("NumberСlient") numberClient: String,
            @Query("orderNumber") orderNumber: String,
            @Query("terminalCode") terminalCode: String
    ): Deferred<OrdersListResponse?>

}