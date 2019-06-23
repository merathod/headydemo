package com.demo.heady.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.heady.model.Categories
import com.demo.heady.model.Product
import com.demo.heady.model.Ranking
import com.demo.heady.model.Variant

@Database(entities = arrayOf(
    Categories::class,
    Product::class, Ranking::class, Variant::class), version = 2)

abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val instance = INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "heardy_database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }

        }
    }

}

