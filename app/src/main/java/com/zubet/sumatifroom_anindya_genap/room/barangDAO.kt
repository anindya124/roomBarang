package com.zubet.sumatifroom_anindya_genap.room

import androidx.room.*

@Dao
interface barangDAO {
    @Insert
    fun addBarang(tbbar: tb_barang)
    @Update
    fun updateBarang(tbbar: tb_barang)
    @Delete
    fun deleteBarang(tbbar: tb_barang)
    @Query("SELECT * FROM tb_barang")
    fun tampilBarang():List<tb_barang>
    @Query ("SELECT * FROM tb_barang WHERE Id =:tbbrg_id")
    fun tampilkansemua(tbbrg_id:Int):List<tb_barang>

}