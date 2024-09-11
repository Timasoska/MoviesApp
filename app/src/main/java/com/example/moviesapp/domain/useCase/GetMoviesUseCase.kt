package com.example.moviesapp.domain.useCase

import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.domain.repository.MovieListRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieListRepository
) {
    suspend operator fun invoke(page: Int): List<Data>{
        return repository.getMovieList(page)
    }
}