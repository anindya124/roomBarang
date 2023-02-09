package com.zubet.sumatifroom_anindya_genap.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class tb_barang (
        @PrimaryKey (autoGenerate = true)
        val Id : Int,
        val NamaBarang : String,
        val Harga : Int,
        val Quantity : Int
        )