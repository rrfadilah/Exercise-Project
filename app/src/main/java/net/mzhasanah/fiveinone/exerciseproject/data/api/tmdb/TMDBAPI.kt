package net.mzhasanah.fiveinone.exerciseproject.data.api.tmdb

import net.mzhasanah.fiveinone.exerciseproject.network.TMDBApiClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPI {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = TMDBApiClient.APIKEY
    ): Response<NowPlayingResponse>
}