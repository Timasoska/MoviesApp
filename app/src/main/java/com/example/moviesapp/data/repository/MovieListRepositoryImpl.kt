package com.example.moviesapp.data.repository

import android.util.Log
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.domain.movies.Data
import com.example.moviesapp.domain.movies.MoviesModel
import com.example.moviesapp.domain.repository.MovieListRepository
import com.example.moviesapp.presentation.MovieViewState
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(private val api: MoviesApi) : MovieListRepository {
    override suspend fun getMovieList(page: Int): List<Data> {
        MovieViewState.Loading
        return try {
            val response = api.getMoviesData(page)
            val moviesModel = response.body()
            moviesModel?.data ?: emptyList()
        } catch (e: Exception) {
            Log.i("Error", "Exception occurred: ${e.localizedMessage}", e)
            emptyList()
        }
    }
}