package com.zubet.sumatifroom_anindya_genap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

   Handler(Looper.getMainLooper()).postDelayed({
       val intent = Intent (this@splashScreen,MainActivity::class.java)
        startActivity(intent)
        finish()
   },3000)
}
}

