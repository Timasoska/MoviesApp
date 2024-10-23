package com.example.moviesapp

import com.example.moviesapp.data.movies.Data
import com.example.moviesapp.domain.repository.MovieListRepository

// Фейковая реализация MovieListRepository для тестов
class FakeMovieListRepository : MovieListRepository {
    override suspend fun getMovieList(page: Int): List<Data> {
        // Возвращаем пустой список фильмов для тестирования
        return listOf()
    }
}
