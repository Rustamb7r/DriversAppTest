package com.example.driversapptest.domain.interactor

import com.example.driversapptest.data.GoodsService
import com.example.driversapptest.data.model.toGoodsList
import com.example.driversapptest.domain.model.Goods
import java.util.*
import javax.inject.Inject

class GetGoodsUseCase @Inject constructor(private val goodsService: GoodsService){
    suspend fun execute(date: String, terminalCode: String): List<Goods>? {
        return goodsService.getPrealertAsync(date, terminalCode).await()?.toGoodsList()
    }
}