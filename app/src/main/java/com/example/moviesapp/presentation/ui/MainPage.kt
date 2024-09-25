package com.example.moviesapp.presentation.ui

import android.util.Log
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesapp.presentation.MovieCommon.MovieViewIntent
import com.example.moviesapp.presentation.MovieCommon.MovieViewState
import com.example.moviesapp.presentation.MovieCommon.MovieViewModel
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewModel


@Composable
fun MainPage(movieViewModel: MovieViewModel, navController: NavController, movieDetailViewModel: MovieDetailViewModel){
    val state by movieViewModel.state
    var isSwipedLeft by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    change.consume()
                    when {
                        dragAmount > 0 -> {
                            // Свайп вправо, загрузка предыдущей страницы
                            Log.d("SwipeGesture", "Swipe Right")
                            if (state is MovieViewState.Success) {
                                movieViewModel.processIntent(MovieViewIntent.PreviousPage)
                                isSwipedLeft = true
                            }
                        }
                        dragAmount < 0 -> {
                            // Свайп влево, загрузка следующей страницы
                            Log.d("SwipeGesture", "Swipe Left")
                            if (state is MovieViewState.Success) {
                                movieViewModel.processIntent(MovieViewIntent.NextPage)
                            }
                        }
                    }
                }
            }
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
                MoviesGrid(movies = movies, navController = navController, movieDetailViewModel = movieDetailViewModel)
            }
        }  // Custom page navigation buttons with icons
        Row(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Current Page Indicator
            val currentPage = (state as? MovieViewState.Success)?.currentPage ?: 1
            Text(
                text = "Page $currentPage",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}