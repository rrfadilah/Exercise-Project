package net.mzhasanah.fiveinone.exerciseproject.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.model.movie.NowPlayingModel
import net.mzhasanah.fiveinone.exerciseproject.network.TMDBApiClient

class MovieViewModel : ViewModel() {

    val shouldShowNowPlaying: MutableLiveData<List<NowPlayingModel>> = MutableLiveData()
    val shouldShowPopular: MutableLiveData<List<NowPlayingModel>> = MutableLiveData()

    fun getNowPlaying() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = TMDBApiClient.instanceTMDB.getNowPlaying()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()
                    // transformasi atau mapping dari response ke model
                    val nowPlayingResponse = response.body()
                    val nowPlayingModels = nowPlayingResponse?.results?.map {
                        NowPlayingModel(
                            id = it.id ?: -1,
                            image = "https://image.tmdb.org/t/p/w500/" + it.posterPath.orEmpty(),
                            title = it.title.orEmpty()
                        )
                    } ?: listOf()
                    nowPlayingModels // yang di parsing ke livedata
                    shouldShowNowPlaying.postValue(nowPlayingModels)
                } else {

                }
            }
        }
    }
}