package com.example.moviesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewIntent
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewModel
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewState

@Composable
fun DetailScreen(movieDetailViewModel: MovieDetailViewModel){
    val state by movieDetailViewModel.state
    when(state){
        is MovieDetailViewState.Loading -> {
            Column(modifier = Modifier.fillMaxSize(),
                Arrangement.Center,
                Alignment.CenterHorizontally)
            {CircularProgressIndicator()}
        }
        is MovieDetailViewState.Error -> {
            val message = (state as MovieDetailViewState.Error).message
            Text(text = "Error: $message")
        }
        is MovieDetailViewState.Success -> {
            val movie = (state as MovieDetailViewState.Success).data
            DetailPage(movie = movie)
        }
    }
}

@Composable
fun DetailPage(movie: Data){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(
            text = movie.title,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
                .padding(4.dp)
        )
        AsyncImage(
            model = movie.poster,
            contentDescription = "Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize(),

        )
    }
}