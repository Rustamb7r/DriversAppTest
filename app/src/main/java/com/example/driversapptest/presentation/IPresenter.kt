package com.example.driversapptest.presentation

interface IPresenter<VIEW> {

    var view: VIEW?

    fun onCreate(view: VIEW)

    fun setPrealertPrint(numberClient: String, orderNumber: String, terminalCode: String)

    fun loadGoods(terminalCode: String)

    fun onDestroy()
}