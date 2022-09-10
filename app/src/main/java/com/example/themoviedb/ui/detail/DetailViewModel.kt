package com.example.themoviedb.ui.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus : Boolean){
        movieUseCase.setFavoriteMovie(movie, newStatus)
    }
}