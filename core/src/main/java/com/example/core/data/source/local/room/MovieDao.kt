package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("select * from movie")
    fun getAllMovie() : Flow<List<MovieEntity>>

    @Query("select * from movie where isFavorite = 1")
    fun getFavoriteMovie() : Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie : List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie : MovieEntity)
}