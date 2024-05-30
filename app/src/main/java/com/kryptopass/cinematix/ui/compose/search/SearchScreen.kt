package com.kryptopass.cinematix.ui.compose.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kryptopass.cinematix.R
import com.kryptopass.domain.entity.Movie

@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResults = remember { mutableStateOf(emptyList<Movie>()) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f),
                label = { Text(stringResource(R.string.search_label)) },
                singleLine = true,
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                    searchMovies(searchText, searchResults)
                })
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedButton(
                onClick = {
                    keyboardController?.hide()
                    searchMovies(searchText, searchResults)
                },
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .padding(vertical = 1.5.dp)
                    .padding(top = 6.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.Transparent
                )
            ) {
                Text(text = "Search")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Results list
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(searchResults.value) { index, movie ->
                MovieItem(movie)
                if (index < searchResults.value.size - 1) {
                    HorizontalDivider()
                }
            }
        }
    }
}

fun searchMovies(query: String, searchResults: MutableState<List<Movie>>) {
    searchResults.value = performSearch(query)
}

@Composable
fun MovieItem(movie: Movie) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = movie.title ?: "", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = movie.overview ?: "", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Release Date: ${movie.releaseDate ?: ""}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun SearchResultItem(result: String) {
    Text(
        text = result,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

fun performSearch(query: String): List<Movie> {
    if (query.isBlank()) return emptyList()
    return listOf(
        Movie(
            title = "The Matrix",
            overview = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            releaseDate = "March 31, 1999"
        ),
        Movie(
            title = "Inception",
            overview = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            releaseDate = "July 16, 2010"
        ),
        Movie(
            title = "The Shawshank Redemption",
            overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            releaseDate = "September 23, 1994"
        ),
        Movie(
            title = "The Godfather",
            overview = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
            releaseDate = "March 24, 1972"
        ),
        Movie(
            title = "The Dark Knight",
            overview = "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            releaseDate = "July 18, 2008"
        ),
        Movie(
            title = "Schindler's List",
            overview = "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
            releaseDate = "February 4, 1994"
        ),
        Movie(
            title = "The Lord of the Rings: The Return of the King",
            overview = "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
            releaseDate = "December 17, 2003"
        ),
        Movie(
            title = "Pulp Fiction",
            overview = "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
            releaseDate = "October 14, 1994"
        ),
        Movie(
            title = "The Lord of the Rings: The Fellowship of the Ring",
            overview = "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.",
            releaseDate = "December 19, 2001"
        ),
        Movie(
            title = "Forrest Gump",
            overview = "The presidencies of Kennedy and Johnson, the events of Vietnam, Watergate and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.",
            releaseDate = "July 6, 1994"
        ),
        Movie(
            title = "Fight Club",
            overview = "An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.",
            releaseDate = "October 15, 1999"
        ),
        Movie(
            title = "Inception",
            overview = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
            releaseDate = "July 16, 2010"
        ),
        Movie(
            title = "The Lord of the Rings: The Two Towers",
            overview = "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.",
            releaseDate = "December 18, 2002"
        ),
        Movie(
            title = "Star Wars: Episode V - The Empire Strikes Back",
            overview = "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader.",
            releaseDate = "May 21, 1980"
        ),
        Movie(
            title = "The Matrix",
            overview = "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
            releaseDate = "March 31, 1999"
        ),
        Movie(
            title = "Goodfellas",
            overview = "The story of Henry Hill and his life in the mob, covering his relationship with his wife Karen Hill and his mob partners Jimmy Conway and Tommy DeVito in the Italian-American crime syndicate.",
            releaseDate = "September 19, 1990"
        ),
        Movie(
            title = "One Flew Over the Cuckoo's Nest",
            overview = "A criminal pleads insanity and is admitted to a mental institution, where he rebels against the oppressive nurse and rallies up the scared patients.",
            releaseDate = "November 19, 1975"
        ),
        Movie(
            title = "The Godfather: Part II",
            overview = "The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",
            releaseDate = "December 20, 1974"
        ),
        Movie(
            title = "The Silence of the Lambs",
            overview = "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.",
            releaseDate = "February 14, 1991"
        ),
        Movie(
            title = "The Usual Suspects",
            overview = "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup.",
            releaseDate = "September 15, 1995"
        )
    )
}

