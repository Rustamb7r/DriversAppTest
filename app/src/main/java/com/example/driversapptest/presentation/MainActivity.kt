package com.example.driversapptest.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.driversapptest.databinding.ActivityMainBinding
import com.example.driversapptest.domain.model.Goods
import com.example.driversapptest.presentation.orders_list.OrderAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {


    @Inject
    lateinit var mainPresenter: MainPresenter
    private var binding: ActivityMainBinding? = null

    private val goodSelected= { numberClient: String, orderNumber: String ->
        mainPresenter.setPrealertPrint(numberClient, orderNumber, "819")
    }

    private val adapter = OrderAdapter(goodSelected)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as BaseApplication).appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.recyclerViewOrder?.adapter = adapter

        // DONT CALL PRESENTER FUNCS BEFORE THIS
        mainPresenter.setMainView(this)

        mainPresenter.loadGoods(terminalCode = "819") // TODO: убрать хардкод
    }

    override fun updateGoodsList(goodsList: List<Goods>) {
        adapter.setItems(goodsList = goodsList)
    }

    override fun onPrealertSuccess(orderNumber: String, numberСlient: String) {
        mainPresenter.loadGoods(terminalCode = "819") // TODO: убрать хардкод
    }

    private var toast: Toast? = null
    override fun showError() {
        if (toast != null) {
            toast?.cancel()
        }
        toast = Toast.makeText(this, "Ошибка при обмене с 1С", Toast.LENGTH_SHORT)
        toast?.show()
    }
}