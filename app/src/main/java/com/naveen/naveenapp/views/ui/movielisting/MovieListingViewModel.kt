package com.naveen.naveenapp.views.ui.movielisting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.naveen.naveenapp.network.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MovieListingViewModel : ViewModel() {

    private val _searchMovieList = MutableLiveData<ListOfMovies>()
    val searchMovieList: LiveData<ListOfMovies> = _searchMovieList

    private val _movieInfo = MutableLiveData<MovieInfo>()
    val movieInfo: LiveData<MovieInfo> = _movieInfo

    private val _isLoadingState = MutableLiveData(false)
    val isLoadingState : LiveData<Boolean> = _isLoadingState

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    val repository : MovieRepository = MovieRepository()


    fun cancelAllRequests() = coroutineContext.cancel()

    private val getSearchMoviesListObserver = Observer<ResultWrapper<ListOfMovies>>{ result ->
        when(result?.status){
            Status.LOADING ->{
                _isLoadingState.value = true
            }
            Status.ERROR ->{
                _isLoadingState.value = false
            }
            Status.SUCCESS ->{
                _searchMovieList.value = result.data
                _isLoadingState.value = false
            }
        }
    }

    fun initSerachObserver(moveName: String){
        repository.searchMoviesLiveDataList.removeObserver(getSearchMoviesListObserver)

        repository.searchMoviesLiveDataList.value = null

        repository.searchMoviesLiveDataList.observeForever(getSearchMoviesListObserver)

        scope.launch {
            try {
                repository.getSearchMovies(moveName)
            } catch (t: Throwable) {
            }
        }
    }

    private val getMoviesInfoObserver = Observer<ResultWrapper<MovieInfo>>{ result ->
        when(result?.status){
            Status.LOADING ->{
                _isLoadingState.value = true
            }
            Status.ERROR ->{
                _isLoadingState.value = false
            }
            Status.SUCCESS ->{
                _movieInfo.value = result.data
                _isLoadingState.value = false
            }
        }
    }

    fun getMovieInfo(moveName: String){
        repository.moviesInfoLiveDataList.removeObserver(getMoviesInfoObserver)

        repository.moviesInfoLiveDataList.value = null

        repository.moviesInfoLiveDataList.observeForever(getMoviesInfoObserver)

        scope.launch {
            try {
                repository.getMovieeInfo(moveName)
            } catch (t: Throwable) {
            }
        }
    }
}