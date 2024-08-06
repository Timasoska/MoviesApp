package com.example.moviesapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviesapp.presentation.MovieViewIntent
import com.example.moviesapp.presentation.MovieViewState
import com.example.moviesapp.presentation.MoviewViewModel

@Composable
fun MainPage(movieViewModel: MoviewViewModel){
    val state by movieViewModel.state

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
        Arrangement.Bottom,
        Alignment.CenterHorizontally
    ){
        when(state){
            is MovieViewState.Loading -> CircularProgressIndicator()
            is MovieViewState.Error -> {
                val message = (state as MovieViewState.Error).message
                Text(text = "Error: $message")
            }
            is MovieViewState.Success -> {
                val movies = (state as MovieViewState.Success).data
                MoviesGrid(movies = movies)
            }
        }
        Button(modifier = Modifier.padding(15.dp)
            ,onClick = {
                movieViewModel.processIntent(MovieViewIntent.LoadNewMovie)
            }) {
            Text(text = "TEST")
        }
    }
}