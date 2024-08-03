package com.example.moviesapp.data.remote

import com.example.moviesapp.domain.movies.Data
import com.example.moviesapp.domain.movies.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi{

    @GET("v1/movies")
    suspend fun getMoviesData(
        @Query("page=%7Bpage%7D")page: Int
    ): Response<List<MoviesModel>>

}