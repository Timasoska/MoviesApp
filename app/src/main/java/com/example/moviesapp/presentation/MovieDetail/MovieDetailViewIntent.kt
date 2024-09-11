package com.example.moviesapp.presentation.MovieDetail

sealed class MovieDetailViewIntent {
    data class LoadMovie(val movieId: Int) : MovieDetailViewIntent()
}