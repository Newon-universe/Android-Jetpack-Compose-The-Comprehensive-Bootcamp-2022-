package com.example.jetnote.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun dateForTimestamp(timeStamp: Long): Date? {
        return Date(timeStamp)
    }
}