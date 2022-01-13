package com.example.driversapptest.domain.interactor

import com.example.driversapptest.data.GoodsService
import javax.inject.Inject

private const val SUCCESS_CODE = "2501"

class SetPrealertPrintUseCase @Inject constructor(private val goodsService: GoodsService) {
    suspend fun execute(numberClient: String, orderNumber: String, terminalCode: String): Boolean {
        return goodsService.setPrealertPrintAsync(numberClient, orderNumber, terminalCode).await()?.resultCode == SUCCESS_CODE
    }
}