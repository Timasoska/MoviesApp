package com.example.moviesapp.navigation

import android.provider.ContactsContract.Data
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesapp.presentation.MovieCommon.MovieViewModel
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewModel
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewState
import com.example.moviesapp.presentation.ui.DetailScreen
import com.example.moviesapp.presentation.ui.MainPage

@Composable
fun CustomNavHost(
    navController: NavHostController,
    movieViewModel : MovieViewModel,
    movieDetailViewModel: MovieDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "MainPage"
    ) {
        composable(route = "MainPage") {
            MainPage(movieViewModel = movieViewModel, navController = navController, movieDetailViewModel = movieDetailViewModel)
        }
        composable(route = "DetailScreen") {
            DetailScreen(movieDetailViewModel = movieDetailViewModel)
        }
    }
}
