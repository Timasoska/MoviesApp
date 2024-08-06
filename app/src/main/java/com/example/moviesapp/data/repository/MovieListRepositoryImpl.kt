package com.example.moviesapp.data.repository

import android.util.Log
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.movies.Data
import com.example.moviesapp.domain.repository.MovieListRepository
import com.example.moviesapp.presentation.MovieViewState
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(private val api: MoviesApi) : MovieListRepository {
    override suspend fun getMovieList(): List<Data> {
        MovieViewState.Loading
        return try {
            val response = api.getMoviesData(1)
            val moviesModel = response.body()
            val dataList = moviesModel?.data ?: emptyList()
            MovieViewState.Success(dataList)
            Log.i("Response", ": ${moviesModel}")
            dataList
        } catch (e: Exception) {
            Log.i("Error", "Exception occurred: ${e.localizedMessage}", e)
            //MovieViewState.Error("Error in catch")
            emptyList()
        }
    }
}