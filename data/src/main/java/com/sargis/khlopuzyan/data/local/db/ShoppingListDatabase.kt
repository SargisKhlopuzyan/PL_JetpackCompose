package com.sargis.khlopuzyan.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sargis.khlopuzyan.data.local.dao.ShoppingListDao
import com.sargis.khlopuzyan.data.local.entity.ShoppingListEntity

@Database(
    entities = [ShoppingListEntity::class],
    version = 1,
    exportSchema = true
)
abstract class ShoppingListDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao

    companion object {
        private const val DATABASE_NAME = "shopping_list_database"

        @Volatile
        private var INSTANCE: ShoppingListDatabase? = null

        fun getInstance(context: Context): ShoppingListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingListDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}