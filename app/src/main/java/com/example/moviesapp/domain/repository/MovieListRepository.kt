package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.movies.Data


interface MovieListRepository {

    suspend fun getMovieList(): List<Data>
}