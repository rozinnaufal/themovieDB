package com.example.themoviedb.ui.detail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.themoviedb.R
import com.example.themoviedb.const.ImgUrl
import com.example.themoviedb.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding : ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = this.getSharedPreferences("extra_data", Context.MODE_PRIVATE)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }
    private fun showDetailMovie(detailMovie : Movie?){
        detailMovie?.let {
            supportActionBar?.title = detailMovie.tittle
            binding.titleDetail.text = detailMovie.tittle
            binding.description.text = detailMovie.description
            Glide.with(this@DetailMovieActivity)
                .load(ImgUrl + detailMovie.image)
                .into(binding.imgDetail)
            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }
    private fun setStatusFavorite(statusFavorite: Boolean) {
        val editor = sharedPreferences.edit()

        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
            editor.putBoolean(EXTRA_DATA, statusFavorite)
            editor.commit()
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }

    }
}