package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.movies.MoviesModel

interface MovieListRepository {

    suspend fun getMovieList(

    ): List<MoviesModel>

}