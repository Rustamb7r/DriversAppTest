package com.example.driversapptest.presentation.orders_list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.driversapptest.databinding.GoodsItemBinding
import com.example.driversapptest.domain.model.Goods
import com.example.driversapptest.presentation.MainPresenter
import javax.inject.Inject

class OrderViewHolder(itemView: View, private val goodSelected: (numberClient: String, orderNumber: String) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val binding = GoodsItemBinding.bind(itemView)

    fun onBind(goods: Goods) { //изменяем значения элементам списка
        binding.numberClient.text = goods.numberСlient
        binding.orderNumber.text = goods.orderNumber
        binding.planTv.text = goods.planOrder.toString()
        binding.button.setOnClickListener(View.OnClickListener { view ->
                goodSelected.invoke(goods.numberСlient, goods.orderNumber)
        })
    }
}