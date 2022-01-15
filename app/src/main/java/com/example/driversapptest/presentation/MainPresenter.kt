package com.example.driversapptest.presentation

import android.util.Log
import com.example.driversapptest.domain.interactor.GetGoodsUseCase
import com.example.driversapptest.domain.interactor.SetPrealertPrintUseCase
import com.example.driversapptest.domain.model.Goods
import com.example.driversapptest.presentation.di.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainPresenter @Inject constructor(
        private val getGoodsUseCase: GetGoodsUseCase,
        private val setPrealertPrintUseCase: SetPrealertPrintUseCase,
        private val dispatchersProvider: DispatchersProvider
) : IPresenter<MainView>, CoroutineScope {

    private val TAG = "MainPresenter"

    override var view: MainView? = null

    private val job = Job() // a lifecycle of a MainPresenter coroutine

    override val coroutineContext: CoroutineContext // guaranties that when it's lifecycle ends everything gonna be cancelled
        get() = job + dispatchersProvider.main

    override fun loadGoods(terminalCode: String) {
        launch {

            val list: List<Goods> = try {
                getGoodsUseCase.execute(date = getCurrentDate(), terminalCode = terminalCode) ?: emptyList()
            } catch (e: Exception) {
                Log.w(TAG, e)
                emptyList()
            }

            view?.updateGoodsList(list)
        }
    }

    override fun setPrealertPrint(numberClient: String, orderNumber: String, terminalCode: String) {
        launch {
            if (setPrealertPrintUseCase.execute(numberClient, orderNumber, terminalCode)) {
                view?.onPrealertSuccess(orderNumber = orderNumber, numberСlient = numberClient)
            }
        }
    }

    fun getCurrentDate(): String {
        return "2021-12-07" // TODO; добавить норм метод обратно когда бэк доделают
//        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
//        val cal = Calendar.getInstance()
//        return dateFormat.format(cal.time)
    }

    override fun onCreate(view: MainView) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

}