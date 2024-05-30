package com.kryptopass.cinematix.ui.compose.movie.single

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(

) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatData(releaseDate: String?): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
        val date = LocalDate.parse(releaseDate, inputFormatter)
        return date.format(outputFormatter)
    }
}