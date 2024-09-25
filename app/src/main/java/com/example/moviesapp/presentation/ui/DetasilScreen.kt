package com.example.moviesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesapp.data.movies.Data
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
            .background(Color(0xFF1B1B1B))
            .padding(16.dp)
    ) {
        // Movie Poster
        AsyncImage(
            model = movie.poster,
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Movie Title and Rating
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.7f), shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = movie.imdb_rating,
                    color = Color.Yellow,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Movie Genres
        Text(
            text = movie.genres.joinToString(", "),
            color = Color.Gray,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Movie Runtime and Release Year
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Runtime: ${movie.runtime}",
                color = Color.LightGray,
                fontSize = 14.sp
            )
            Text(
                text = "Year: ${movie.year}",
                color = Color.LightGray,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Movie Plot
        Text(
            text = movie.plot,
            color = Color.White,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Movie Director, Actors
        Text(
            text = "Director: ${movie.director}",
            color = Color.LightGray,
            fontSize = 14.sp
        )
        Text(
            text = "Actors: ${movie.actors}",
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}