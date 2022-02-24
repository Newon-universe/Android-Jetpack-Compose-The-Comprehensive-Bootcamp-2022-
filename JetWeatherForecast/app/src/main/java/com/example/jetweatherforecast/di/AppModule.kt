package com.example.jetweatherforecast.di

import android.content.Context
import androidx.room.Room
import com.example.jetweatherforecast.data.WeatherDao
import com.example.jetweatherforecast.data.WeatherDatabase
import com.example.jetweatherforecast.navigation.WeatherAPI
import com.example.jetweatherforecast.util.Constants
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

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context, // DB 는 앱 내의 정보들이 필요해요 !!
            WeatherDatabase::class.java,  // DB 를 어떻게 만들까슝?
            "weather_database" // DB 이름은 뭐로 할까요?
        )
            .fallbackToDestructiveMigration()  // DB 가 이전 버전이거나 migration 등이 되는 경우에, 걍 새로 파셈
            .build() // 이렇게 만들어줘, 해 줘

}