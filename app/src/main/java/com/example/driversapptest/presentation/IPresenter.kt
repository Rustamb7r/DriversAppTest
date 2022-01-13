package com.example.driversapptest.presentation

interface IPresenter<VIEW> {

    var view: VIEW?

    fun onCreate(view: VIEW)

    fun onDestroy()
}