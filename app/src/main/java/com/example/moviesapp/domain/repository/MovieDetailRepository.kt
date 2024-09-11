package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.movies.Data

interface MovieDetailRepository {
    suspend fun getMovieId(movieId: Int): Data
}