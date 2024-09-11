package com.example.moviesapp.presentation.MovieCommon

import com.example.moviesapp.data.movies.Data

sealed class MovieViewState {
    data class Success(val data: List<Data>, val currentPage: Int): MovieViewState()
    object Loading: MovieViewState()
    data class Error(val message: String): MovieViewState()
}