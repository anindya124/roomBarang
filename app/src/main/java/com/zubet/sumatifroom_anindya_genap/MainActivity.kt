package com.zubet.sumatifroom_anindya_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.zubet.sumatifroom_anindya_genap.room.Constant
import com.zubet.sumatifroom_anindya_genap.room.codepelita
import com.zubet.sumatifroom_anindya_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy {codepelita(this) }
    lateinit var barangadpater :BarangAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setupRV()
    }

    override fun onStart() {
        super.onStart()
        loadData()
            }
            fun loadData(){
                CoroutineScope(Dispatchers.IO).launch {
                    val barang = db.tbbarangDAO().tampilBarang()
                    Log.d("MainActivity","dbResponse:$barang")
                    withContext(Dispatchers.Main){
                        barangadpater.setData(barang)
            }
        }
    }

     private fun halEdit(){
        BtnInput.setOnClickListener {
            intentEdit(0,Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(tbgudangbarang:Int,intentType:Int){
        startActivity(Intent(applicationContext,EditActivity::class.java)
            .putExtra("intent_id",tbgudangbarang)
            .putExtra("intent_type",intentType))
    }

    fun setupRV(){
        barangadpater = BarangAdapter(arrayListOf(),object :BarangAdapter.OnAdapterListener{
            override fun OnClick(tbbar: tb_barang) {
                intentEdit(tbbar.Id,Constant.TYPE_READ)
            }

            override fun Onupdate(tbbar: tb_barang) {
                intentEdit(tbbar.Id,Constant.TYPE_UPDATE)
            }

            override fun Ondelete(tbbar: tb_barang) {
            deleteAlert(tbbar)
            }

        })
        listBarang.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = barangadpater
        }
    }

    private fun deleteAlert(tbbar :tb_barang){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Hapus ${tbbar.NamaBarang}?")
            setNegativeButton("Batal"){dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus"){dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbbarangDAO().deleteBarang(tbbar)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
    dialog.show()
    }
}