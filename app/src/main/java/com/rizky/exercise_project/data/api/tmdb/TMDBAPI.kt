package com.rizky.exercise_project.data.api.tmdb

import com.rizky.exercise_project.network.TMDBApiClient
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * com.rizky.exercise_project.data.api.tmdb
 *
 * Created by Rizky Fadilah on 23/04/22.
 * https://github.com/rizkyfadilah
 *
 */

interface TMDBAPI {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String = TMDBApiClient.APIKEY
    ): Response<NowPlayingResponse>
}
