package com.zubet.sumatifroom_anindya_genap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zubet.sumatifroom_anindya_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_barang_adapter.view.*

class BarangAdapter(private val goods: ArrayList<tb_barang>, private val listener : OnAdapterListener):
    RecyclerView.Adapter<BarangAdapter.BarangViewHolder>() {

    class BarangViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        return BarangViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_barang_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val barr = goods[position]
        holder.view.Tid.text = barr.Id.toString()
        holder.view.Tnamabarang.text = barr.NamaBarang
        holder.view.cvEdit.setOnClickListener{
            listener.OnClick(barr)
        }
        holder.view.imageEdit.setOnClickListener{
            listener.Onupdate(barr)
        }
        holder.view.imageDelete.setOnClickListener{
            listener.Ondelete(barr)
        }
    }

    override fun getItemCount() = goods.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<tb_barang>){
        goods.clear()
        goods.addAll(list)
        notifyDataSetChanged()
    }
    interface OnAdapterListener{
        fun OnClick (tbbar : tb_barang)
        fun Onupdate (tbbar: tb_barang)
        fun Ondelete (tbbar: tb_barang)
    }
}