package com.example.moviesapp.presentation.MovieDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.useCase.GetDetailMovieUseCase
import com.example.moviesapp.presentation.MovieCommon.MovieViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase
) : ViewModel(){
    private val _state = mutableStateOf<MovieDetailViewState>(MovieDetailViewState.Loading)
    var state : State<MovieDetailViewState> = _state

    init{
        processIntent(MovieDetailViewIntent.LoadMovie)
    }

    fun processIntent(intent: MovieDetailViewIntent){
        when(intent){
            is MovieDetailViewIntent.LoadMovie -> loadDetailMovie(1)
        }
    }

    private fun loadDetailMovie(movieId: Int){
        viewModelScope.launch {
            _state.value = MovieDetailViewState.Loading
            try{
                val movie = getDetailMovieUseCase(movieId)
                _state.value = MovieDetailViewState.Success(movie, movieId)
            } catch (e: Exception){
                _state.value = MovieDetailViewState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

}