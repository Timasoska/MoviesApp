package com.example.moviesapp

import androidx.compose.runtime.mutableStateOf
import com.example.moviesapp.presentation.MovieCommon.MovieViewIntent
import com.example.moviesapp.presentation.MovieCommon.MovieViewModel
import com.example.moviesapp.presentation.MovieCommon.MovieViewState
import com.example.moviesapp.domain.useCase.GetMoviesUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class ExampleUnitTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        // Фейковый репозиторий для тестов
        val fakeMovieListRepository = FakeMovieListRepository()
        getMoviesUseCase = GetMoviesUseCase(fakeMovieListRepository)

        // Инициализация ViewModel
        viewModel = MovieViewModel(getMoviesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial load movies`() = runTest {
        viewModel.processIntent(MovieViewIntent.LoadNewMovie)

        // Прогоняем корутины
        advanceUntilIdle()

        // Проверяем, что состояние - загрузка
        assertTrue(viewModel.state.value is MovieViewState.Success)
        assertEquals(1, (viewModel.state.value as MovieViewState.Success).currentPage)
    }


    @Test
    fun `test swipe from first page does not go below 1`() = runTest {
        // Инициализация первой страницы
        viewModel.state = mutableStateOf(MovieViewState.Success(emptyList(), 1))

        viewModel.processIntent(MovieViewIntent.PreviousPage)

        // Прогоняем корутины
        advanceUntilIdle()

        val state = viewModel.state.value
        assertTrue(state is MovieViewState.Success)
        assertEquals(1, (state as MovieViewState.Success).currentPage)
    }
}
