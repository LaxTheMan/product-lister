package com.example.productlister.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.*

class Converters {

    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date {
        return Date(value)
    }

//    @TypeConverter
//    fun toBitmap(bytes: ByteArray): Bitmap {
//        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//    }
//
//    @TypeConverter
//    fun fromBitmap(bmp: Bitmap): ByteArray {
//        val outputStream = ByteArrayOutputStream()
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//        return outputStream.toByteArray()
//    }

}