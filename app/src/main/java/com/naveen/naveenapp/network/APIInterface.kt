package com.naveen.naveenapp.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("?apikey=b9bd48a6&type=movie")
    fun getSerachMovies(@Query(value = "s") movieName: String): Deferred<Response<ListOfMovies>>

    @GET("?apikey=b9bd48a6")
    fun gethMovieInfo(@Query(value = "i") movieID: String): Deferred<Response<MovieInfo>>
}