package com.wix.network

import com.wix.dto.Movie
import com.wix.dto.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIES = "movies.json"
        private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET(MOVIES)
    fun movies(): Call<List<Movie>>

    @GET(MOVIE_DETAILS)
    fun movieDetails(@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetails>
}