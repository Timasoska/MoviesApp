package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.navigation.CustomNavHost
import com.example.moviesapp.presentation.MovieCommon.MovieViewModel
import com.example.moviesapp.presentation.MovieDetail.MovieDetailViewModel
import com.example.moviesapp.presentation.ui.MainPage
import com.example.moviesapp.presentation.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()
    private val detailViewModel: MovieDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    CustomNavHost(navController = navController, movieViewModel = viewModel, movieDetailViewModel = detailViewModel)
                    //MainPage(movieViewModel = viewModel, movieDetailViewModel = detailViewModel)
                }
            }
        }
    }
}
