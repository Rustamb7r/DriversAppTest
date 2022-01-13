package com.example.driversapptest.presentation

import com.example.driversapptest.domain.model.Goods

interface MainView {

    fun updateGoodsList(goodsList: List<Goods>)

    fun onPrealertSuccess(orderNumber: String, numberСlient: String)

    fun showError()
}