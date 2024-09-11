package com.example.moviesapp.data.remote

import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.data.movies.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi{

    @GET("v1/movies")
    suspend fun getMoviesData(
        @Query("page")page: Int
    ): Response<MoviesModel>

    @GET("v1/movies")
    suspend fun getMovieDetail(
        @Query("movieId") movieId: Int
    ):Response<Data>

}