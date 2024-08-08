package com.example.moviesapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ){
        when(state){
            is MovieViewState.Loading -> {
                Column(modifier = Modifier.fillMaxSize(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally)
                {CircularProgressIndicator()}
            }
            is MovieViewState.Error -> {
                val message = (state as MovieViewState.Error).message
                Text(text = "Error: $message")
            }
            is MovieViewState.Success -> {
                val movies = (state as MovieViewState.Success).data
                MoviesGrid(movies = movies)
            }
        }
        Row(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                movieViewModel.processIntent(MovieViewIntent.PreviousPage)
            }) {
                Text(text = "<")
            }
            Button(onClick = {
                movieViewModel.processIntent(MovieViewIntent.NextPage)
            }) {
                Text(text = ">")
            }
        }
    }
}