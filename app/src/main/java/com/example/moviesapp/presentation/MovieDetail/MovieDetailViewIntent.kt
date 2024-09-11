package com.example.moviesapp.presentation.MovieDetail

sealed class MovieDetailViewIntent {
    object LoadMovie : MovieDetailViewIntent()
}