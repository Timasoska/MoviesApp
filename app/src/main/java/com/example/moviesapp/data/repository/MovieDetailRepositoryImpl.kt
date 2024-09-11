package com.example.moviesapp.data.repository

import android.util.Log
import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val api: MoviesApi) : MovieDetailRepository {
    override suspend fun getMovieId(movieId: Int): Data {
        val response = api.getMovieDetail(movieId)
        if(!response.isSuccessful){
            val errorBody = response.errorBody()?.toString()
            Log.e("Response", "$errorBody")
            throw Exception("Response $errorBody")
        }
        return response.body() ?: throw Exception("Response is null")
    }
}