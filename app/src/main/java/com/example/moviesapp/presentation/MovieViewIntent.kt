package com.example.moviesapp.presentation

sealed class MovieViewIntent {
    object LoadNewMovie : MovieViewIntent()
    object NextPage : MovieViewIntent()
    object PreviousPage : MovieViewIntent()
}