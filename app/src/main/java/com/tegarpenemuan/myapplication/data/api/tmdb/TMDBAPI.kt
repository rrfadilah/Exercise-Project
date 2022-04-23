package com.tegarpenemuan.myapplication.data.api.tmdb

import com.tegarpenemuan.myapplication.network.TMDBApiClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPI {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = TMDBApiClient.APIKEY
    ): Response<NowPlayingResponse>
}