package com.example.moviesapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.data.repository.MovieListRepositoryImpl
import com.example.moviesapp.domain.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviewViewModel @Inject constructor(
    //private val api: MoviesApi
    private val repository: MovieListRepository
) : ViewModel() {
    private val _state = mutableStateOf<MovieViewState>(MovieViewState.Loading)
    var state : State<MovieViewState> = _state

    fun processIntent(intent: MovieViewIntent){
       when(intent){
           is MovieViewIntent.LoadNewMovie -> {
               viewModelScope.launch {
                   //val movies = MovieListRepositoryImpl(api).getMovieList()
                   val movies = repository.getMovieList()
                   _state.value = MovieViewState.Success(movies)
               }
           }
       }
    }

}