package com.zubet.sumatifroom_anindya_genap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.zubet.sumatifroom_anindya_genap.room.Constant
import com.zubet.sumatifroom_anindya_genap.room.codepelita
import com.zubet.sumatifroom_anindya_genap.room.tb_barang
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private val db by lazy { codepelita(this) }
    private var tbgudangbarang : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        tbgudangbarang = intent.getIntExtra("intent_id",tbgudangbarang)
        Toast.makeText(this,tbgudangbarang.toString(),Toast.LENGTH_SHORT).show()
        simpandata()
        setupview()
        tampilgoods()
    }
    private fun setupview(){
        val intentType = intent.getIntExtra("intent_type",0 )
        when (intentType){
            Constant.TYPE_CREATE->{
                btnupdate.visibility= View.GONE
            }
            Constant.TYPE_READ ->{
                Btnsave.visibility= View.GONE
                btnupdate.visibility = View.GONE
                tampilgoods()
            }
            Constant.TYPE_UPDATE -> {
                Btnsave.visibility=View.GONE
                tampilgoods()
            }
        }
    }
    private fun simpandata(){
        Btnsave.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.tbbarangDAO().addBarang(
                    tb_barang(ETid.text.toString().toInt(),
                        ETnamabarang.text.toString(),
                        ETharga.text.toString().toInt(),
                        ETquantity.text.toString().toInt())
                )
                finish()
            }
        }
        btnupdate.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.tbbarangDAO().updateBarang(
                    tb_barang(tbgudangbarang,
                        ETnamabarang.text.toString(),
                        ETharga.text.toString().toInt(),
                        ETquantity.text.toString().toInt())
                )
            }
        }
    }
    private fun tampilgoods(){
        tbgudangbarang = intent.getIntExtra("intent_id",0)
        CoroutineScope(Dispatchers.IO).launch {
            val brg = db.tbbarangDAO().tampilkansemua(tbgudangbarang)[0]
            val id : String = brg.Id.toString()
            val harga : String = brg.Harga.toString()
            val QTY : String = brg.Quantity.toString()

            ETid.setText(id)
            ETharga.setText(harga)
            ETquantity.setText(QTY)
            ETnamabarang.setText(brg.NamaBarang)
        }
    }
}