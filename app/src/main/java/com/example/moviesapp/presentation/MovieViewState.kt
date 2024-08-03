package com.example.moviesapp.presentation

import android.provider.ContactsContract.Data

sealed class MovieViewState {
    data class Success(val data: List<Data>): MovieViewState()
    object Loading: MovieViewState()
    data class Error(val message: String): MovieViewState()
}