package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.movies.Data



interface MovieListRepository {
    suspend fun getMovieList(page: Int): List<Data>
}