package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>) : List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                description = it.overview,
                title = it.title,
                isFavorite = false,
                picture = it.poster_path
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>) : List<Movie> =
        input.map {
            Movie(
                movieId = it.id,
                description = it.description,
                isFavorite = it.isFavorite,
                image = it.picture,
                tittle = it.title
            )
        }

    fun mapDomainToEntity(input : Movie) = MovieEntity(
        id = input.movieId,
        title = input.tittle,
        description = input.description,
        picture = input.image,
        isFavorite = false
    )
}