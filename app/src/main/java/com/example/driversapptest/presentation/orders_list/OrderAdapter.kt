package com.example.driversapptest.presentation.orders_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.driversapptest.R
import com.example.driversapptest.domain.model.Goods

class OrderAdapter(
        private val goodSelected: (numberClient: String, orderNumber: String) -> Unit
) : RecyclerView.Adapter<OrderViewHolder>() { // TODO: change to ListAdapter, find out difference between ListAdapter and regular adapter


    private val goodsList = ArrayList<Goods>() // TODO: add diffutil (в ListAdapter будет встроенный можно его использовать)

    fun setItems(goodsList: List<Goods>) {
        this.goodsList.clear()
        this.goodsList.addAll(goodsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder { // TODO: rework to viewbinding
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.goods_item, parent, false)
        return OrderViewHolder(view, goodSelected)
    }

    override fun onBindViewHolder( //созданым VH задаем новые значения
            holder: OrderViewHolder,
            position: Int
    ) {
        holder.onBind(goodsList[position])
    }

    override fun getItemCount(): Int { //возвращает общее колличество элементов в списке
        return goodsList.size
    }

    fun removeItem(orderNumber: String, numberСlient: String) {
        goodsList.remove(goodsList.firstOrNull { it.orderNumber ==  orderNumber && it.numberСlient == numberСlient})
        notifyDataSetChanged()
    }
}