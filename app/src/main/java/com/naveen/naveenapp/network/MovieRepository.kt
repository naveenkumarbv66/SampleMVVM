package com.naveen.naveenapp.network

import android.util.Log

class MovieRepository : BaseRepository() {

    suspend fun getSearchMovies(moveName: String){
        searchMoviesLiveDataList.postValue(ResultWrapper.loading())
        val response = NetworkClient.apiInterface.getSerachMovies(moveName).await()
        if(response.isSuccessful){
            searchMoviesList = response.body()
            searchMoviesLiveDataList.postValue(ResultWrapper.success(response.body()))
            Log.d("Naveen","====>>> Success "+response.body())
        }else{
            searchMoviesLiveDataList.postValue(ResultWrapper.error("Something went wrong"))
            Log.d("Naveen","====>>> Error <<================"+ response.code().toString())
        }
    }

    suspend fun getMovieeInfo(moveName: String){
        Log.d("Naveen","====> Sending : "+moveName)
        moviesInfoLiveDataList.postValue(ResultWrapper.loading())
        val response = NetworkClient.apiInterface.gethMovieInfo(moveName).await()
        Log.d("Naveen","====>>> O/P ")
        if(response.isSuccessful){
            moviesInfo = response.body()
            moviesInfoLiveDataList.postValue(ResultWrapper.success(response.body()))
            Log.d("Naveen","====>>> Success "+response.body())
        }else{
            moviesInfoLiveDataList.postValue(ResultWrapper.error("Something went wrong"))
            Log.d("Naveen","====>>> Error <<================"+ response.code().toString())
        }

    }

}