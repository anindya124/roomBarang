package com.zubet.sumatifroom_anindya_genap.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(
    entities = [tb_barang::class],
    version = 1
)
abstract class codepelita: RoomDatabase() {
    abstract fun tbbarangDAO(): barangDAO

    companion object{
        @Volatile private var instance : codepelita? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance?: buildDatabase(context).also{
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            codepelita::class.java,
            "205385_db"
        ).build()
    }
}