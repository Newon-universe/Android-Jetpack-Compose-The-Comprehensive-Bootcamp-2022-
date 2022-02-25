package com.example.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherforecast.model.Favorite
import com.example.jetweatherforecast.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}

// abstract fun weatherDao(): WeatherDao 넣는 이유 : WeatherDatabase 선언할 때 꼭 선언하라고
// 또는 어떠한 객체가 필요할 때, 이걸 쓰라고 (weatherDao 에 대한 정보가 필요하다면, 이걸 쓰라고)