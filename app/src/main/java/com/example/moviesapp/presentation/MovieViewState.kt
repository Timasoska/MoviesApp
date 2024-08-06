package com.example.moviesapp.presentation

import com.example.moviesapp.domain.movies.Data
import com.example.moviesapp.domain.movies.MoviesModel

sealed class MovieViewState {
    data class Success(val data: List<Data>): MovieViewState()
    object Loading: MovieViewState()
    data class Error(val message: String): MovieViewState()
}