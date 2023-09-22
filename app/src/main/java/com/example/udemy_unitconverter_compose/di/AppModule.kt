package com.example.udemy_unitconverter_compose.di

import android.app.Application
import androidx.room.Room
import com.example.udemy_unitconverter_compose.data.ConverterDatabase
import com.example.udemy_unitconverter_compose.data.ConverterRepository
import com.example.udemy_unitconverter_compose.data.ConverterRepositoryImpl
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
    fun providesConverterDatabase(app: Application): ConverterDatabase {
        return Room.databaseBuilder(
            app,
            ConverterDatabase::class.java,
            "converter_data_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providesConverterRepository(converterDatabase: ConverterDatabase): ConverterRepository {
        return ConverterRepositoryImpl(converterDatabase.converterDao)
    }
}