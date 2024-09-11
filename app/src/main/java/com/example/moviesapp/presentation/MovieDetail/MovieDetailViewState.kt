package com.example.moviesapp.presentation.MovieDetail

import com.example.moviesapp.data.movies.Data

sealed class MovieDetailViewState {
    data class Success(val data: Data, val movieID: Int): MovieDetailViewState()
    object Loading: MovieDetailViewState()
    data class Error(val message: String): MovieDetailViewState()
}