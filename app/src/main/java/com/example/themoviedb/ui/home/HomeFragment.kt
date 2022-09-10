package com.example.themoviedb.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.ui.MovieAdapter
import com.example.themoviedb.R
import com.example.themoviedb.databinding.FragmentHomeBinding
import com.example.themoviedb.ui.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter : MovieAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpRecyclerView()
        return root
    }

    private fun setUpRecyclerView(){
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick ={
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, it)
            startActivity(intent)
        }
        if (activity != null) {

            binding.rvMovie.setHasFixedSize(true)
            homeViewModel.movie.observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is com.example.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is com.example.core.data.Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setList(it.data)
                        }
                        is com.example.core.data.Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = it.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
            with(binding.rvMovie) {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}