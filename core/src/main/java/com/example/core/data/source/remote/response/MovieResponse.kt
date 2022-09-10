package com.example.core.data.source.remote.response

data class MovieResponse(
	val overview: String,
	val title: String,
	val poster_path: String? = null,
	val id: Int,
)

