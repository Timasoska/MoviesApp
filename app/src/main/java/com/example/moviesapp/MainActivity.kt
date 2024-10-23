package com.example.moviesapp

import android.annotation.SuppressLint
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
import android.content.Context
import android.widget.Toast

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()
    private val detailViewModel: MovieDetailViewModel by viewModels()
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    CustomNavHost(navController = navController, movieViewModel = viewModel, movieDetailViewModel = detailViewModel)

                    val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

                    if (isFirstLaunch) {
                        // Показываем Toast с инструкцией
                        Toast.makeText(this, "Swipe left or right to navigate between pages!", Toast.LENGTH_LONG).show()

                        // После показа обновляем значение, чтобы больше не показывалось
                        sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
                    }
                }
            }
        }
    }
}






