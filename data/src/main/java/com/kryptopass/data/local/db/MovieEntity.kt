package com.kryptopass.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long? = 0,
    @ColumnInfo(name = "adult") val adult: Boolean? = null,
    @ColumnInfo(name = "backdropPath") val backdropPath: String? = null,
    @ColumnInfo(name = "genreIds") val genreIds: List<Int?>? = null,
    @ColumnInfo(name = "movieId") val movieId: Int? = null,
    @ColumnInfo(name = "originalLanguage") val originalLanguage: String? = null,
    @ColumnInfo(name = "originalTitle") val originalTitle: String? = null,
    @ColumnInfo(name = "overview") val overview: String? = null,
    @ColumnInfo(name = "popularity") val popularity: Double? = null,
    @ColumnInfo(name = "posterPath") val posterPath: String? = null,
    @ColumnInfo(name = "releaseDate") val releaseDate: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "video") val video: Boolean? = null,
    @ColumnInfo(name = "voteAverage") val voteAverage: Double? = null,
    @ColumnInfo(name = "voteCount") val voteCount: Int? = null
)