package com.example.moviesapp.presentation.MovieCommon

sealed class MovieViewIntent {
    object LoadNewMovie : MovieViewIntent()
    object NextPage : MovieViewIntent()
    object PreviousPage : MovieViewIntent()
}