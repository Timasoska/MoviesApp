package com.example.moviesapp.domain.useCase

import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: MovieDetailRepository
){
    suspend operator fun invoke(movieId: Int): Data{
        return repository.getMovieId(movieId)
    }
}