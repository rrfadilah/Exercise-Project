package net.mzhasanah.fiveinone.exerciseproject.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import net.mzhasanah.fiveinone.exerciseproject.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityMovieBinding
    val viewModel: MovieViewModel by viewModels()
    //    val nowPlayingAdapter: NowPlayingAdapter by lazy { NowPlayingAdapter(emptyList()) }
    lateinit var nowPlayingAdapter: NowPlayingAdapter
    val popularAdapter: NowPlayingAdapter by lazy { NowPlayingAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nowPlayingAdapter = NowPlayingAdapter(emptyList())

        binding.rvNowplaying.adapter = nowPlayingAdapter
        binding.rvPopular.adapter = popularAdapter

        viewModel.getNowPlaying()
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.shouldShowNowPlaying.observe(this) {
            nowPlayingAdapter.updateList(it)
        }

        viewModel.shouldShowPopular.observe(this) {
            popularAdapter.updateList(it)
        }
    }
}