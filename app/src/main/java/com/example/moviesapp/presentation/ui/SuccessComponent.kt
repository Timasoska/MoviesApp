package com.example.moviesapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.data.movies.MoviesModel
import com.example.moviesapp.presentation.MovieCommon.MovieViewState
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewIntent
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewModel
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewState


@Composable
fun MovieCard(movie: Data, onClick: () -> Unit){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .clickable { onClick() }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ){
                AsyncImage(
                    model = movie.poster,
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = movie.imdb_rating,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.7f))
                        .padding(4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = movie.genres.toString(),
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 1
            )
        }
    }
}

@Composable
fun MoviesGrid(movies: List<Data>, navController: NavController,movieDetailViewModel: MovieDetailViewModel, ){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies.size){index ->
            MovieCard(movie = movies[index], onClick = {
                val movieId = movies[index].id
                movieDetailViewModel.processIntent(MovieDetailViewIntent.LoadMovie(movieId))
                navController.navigate("DetailScreen"){
                    launchSingleTop = true // Позволяет избежать дублирования
                }
            })
        }
    }
}
