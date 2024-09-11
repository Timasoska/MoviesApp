package com.example.moviesapp.presentation.MovieCommon

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.useCase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _state = mutableStateOf<MovieViewState>(MovieViewState.Loading)
    var state : State<MovieViewState> = _state

    init {
        processIntent(MovieViewIntent.LoadNewMovie)
    }

    fun processIntent(intent: MovieViewIntent){
       when(intent){
           is MovieViewIntent.LoadNewMovie -> loadMovies(1)
           //is MovieViewIntent.LoadNewMovie -> loadMovies(0)
           is MovieViewIntent.NextPage -> {
               val currentPage = (_state.value as MovieViewState.Success).currentPage ?: 1
               loadMovies(currentPage + 1)
           }
           is MovieViewIntent.PreviousPage -> {
               val currentPage = (_state.value as MovieViewState.Success).currentPage ?: 1
               if(currentPage > 1)
                   loadMovies(currentPage - 1)
           }
       }
    }

    private fun loadMovies(page: Int) {
        viewModelScope.launch {
            _state.value = MovieViewState.Loading
            try {
                val movies = getMoviesUseCase(page)
                _state.value = MovieViewState.Success(movies, page)
            } catch (e: Exception) {
                _state.value = MovieViewState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}