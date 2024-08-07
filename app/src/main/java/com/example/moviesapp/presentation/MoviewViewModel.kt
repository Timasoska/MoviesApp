package com.example.moviesapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.data.repository.MovieListRepositoryImpl
import com.example.moviesapp.domain.repository.MovieListRepository
import com.example.moviesapp.domain.useCase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviewViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _state = mutableStateOf<MovieViewState>(MovieViewState.Loading)
    var state : State<MovieViewState> = _state

    private var currentPage = 1

    init {
        processIntent(MovieViewIntent.LoadNewMovie)
    }

    fun processIntent(intent: MovieViewIntent){
       when(intent){
           is MovieViewIntent.LoadNewMovie -> loadMovies(currentPage)
           is MovieViewIntent.NextPage -> {
               currentPage += 1
               loadMovies(currentPage)
           }
           is MovieViewIntent.PreviousPage -> {
               if (currentPage > 1) {
                   currentPage -= 1
                   loadMovies(currentPage)
               }
           }
       }
    }
    private fun loadMovies(page: Int) {
        viewModelScope.launch {
            _state.value = MovieViewState.Loading
            try {
                val movies = getMoviesUseCase(page)
                _state.value = MovieViewState.Success(movies)
            } catch (e: Exception) {
                _state.value = MovieViewState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}