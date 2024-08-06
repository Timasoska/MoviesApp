package com.example.moviesapp.di

import com.example.moviesapp.data.remote.MoviesApi
import com.example.moviesapp.data.repository.MovieListRepositoryImpl
import com.example.moviesapp.domain.repository.MovieListRepository
import com.example.moviesapp.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi{
        return Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieListRepository(api: MoviesApi): MovieListRepository {
        return MovieListRepositoryImpl(api)
    }
}