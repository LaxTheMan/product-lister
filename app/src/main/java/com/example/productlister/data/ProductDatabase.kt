package com.example.productlister.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.productlister.domain.model.Product

@TypeConverters(Converters::class)
@Database(
    entities =[Product::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase:RoomDatabase() {

    abstract val productDao: ProductDao
}