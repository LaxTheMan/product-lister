package com.example.productlister.di

import android.app.Application
import androidx.room.Room
import com.example.productlister.common.Constants.DATABASE_NAME
import com.example.productlister.data.ProductDao
import com.example.productlister.data.ProductDatabase
import com.example.productlister.data.repository.ProductRepository
import com.example.productlister.data.repository.ProductRepositoryImpl
import com.example.productlister.domain.use_cases.DeleteProduct
import com.example.productlister.domain.use_cases.GetProducts
import com.example.productlister.domain.use_cases.UpsertProduct
import com.example.productlister.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(app, ProductDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideProductRepository(db: ProductDatabase): ProductRepository {
        return ProductRepositoryImpl(db.productDao)
    }

    @Provides
    @Singleton
    fun provideProductUseCases(repository: ProductRepository): UseCases {
        return UseCases(
            getProducts = GetProducts(repository),
            upsertProduct = UpsertProduct(repository),
            deleteProduct = DeleteProduct(repository)
        )
    }

}