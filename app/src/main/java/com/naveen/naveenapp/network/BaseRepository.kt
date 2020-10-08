package com.naveen.naveenapp.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    val searchMoviesLiveDataList = MutableLiveData<ResultWrapper<ListOfMovies>>()
    var searchMoviesList : ListOfMovies? = null

    val moviesInfoLiveDataList = MutableLiveData<ResultWrapper<MovieInfo>>()
    var moviesInfo : MovieInfo? = null

}