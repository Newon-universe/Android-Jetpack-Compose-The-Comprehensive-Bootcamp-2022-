package com.example.jetweatherforecast.di

import com.example.jetweatherforecast.navigation.WeatherAPI
import com.example.jetweatherforecast.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // 직접 호출하지 않고, hilt 와 함께 밑단에서 Retrofit 이나 Dao 가 필요한 곳에 의존성을 주입하도록 설계될 예정
    @Provides
    @Singleton
    fun provideOpenWeatherApi(): WeatherAPI =
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)



}