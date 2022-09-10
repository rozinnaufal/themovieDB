package com.example.core.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.core.databinding.ItemRowMovieBinding
import com.example.core.domain.model.Movie
import com.example.themoviedb.const.ImgUrl

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.UserViewHolder>() {
    private lateinit var binding : ItemRowMovieBinding
    private var listData = ArrayList<Movie>()
    var onItemClick : ((Movie) -> Unit)? = null

    fun setList(users: List<Movie>?) {
        if (users == null) return
        listData.clear()
        listData.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class UserViewHolder(val binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(movie : Movie){
            with(itemView) {
                binding.apply {
                    Glide.with(itemView.context)
                        .load(ImgUrl + movie.image)
                        .apply(RequestOptions().override(150, 200))
                        .into(imgItemPhoto)
                }
            }

            Log.d(MovieAdapter::class.java.simpleName,"User : $movie ")
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}