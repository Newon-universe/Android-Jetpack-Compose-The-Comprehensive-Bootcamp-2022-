package com.example.giphy.di

import android.content.Context
import androidx.room.Room
import com.example.giphy.data.GiphyDao
import com.example.giphy.data.GiphyDatabase
import com.example.giphy.network.HomeAPI
import com.example.giphy.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHomeApi(): HomeAPI =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeAPI::class.java)

    @Provides
    @Singleton
    fun provideGiphyDatabase(@ApplicationContext context: Context): GiphyDatabase =
        Room.databaseBuilder(
            context,
            GiphyDatabase::class.java,
            "giphy_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideGiphyDao(giphyDatabase: GiphyDatabase): GiphyDao =
        giphyDatabase.giphyDao()
}
