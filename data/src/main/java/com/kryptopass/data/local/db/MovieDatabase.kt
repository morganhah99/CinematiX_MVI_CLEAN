package com.kryptopass.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(MovieConverters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

class MovieConverters {

    @TypeConverter
    fun listToJsonString(value: List<Int?>?): String? = Gson().toJson(value)
    @TypeConverter
    fun jsonStringToList(value: String?) =
        Gson().fromJson(value, Array<Int?>::class.java).toList()
}