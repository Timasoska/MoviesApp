package com.example.moviesapp.presentation

sealed class MovieViewIntent {
    object LoadNewMovie : MovieViewIntent()
}