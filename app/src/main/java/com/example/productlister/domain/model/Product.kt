package com.example.productlister.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "consumer_product")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val price: Int,
    val pricePerG: Float,
    val netWt: Int,
    val img1: String?,
    val img2: String?,
    val img3: String?,
    val dateAdded: Date,
    val lastAccessed: Date,
)