
package com.naveen.naveenapp.network
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.naveen.naveenapp.adapter.RowClickListener
import com.squareup.picasso.Picasso

data class Search (
    val Title : String,
    val Year : Int,
    val imdbID : String,
    val Type : String,
    val Poster : String
)

data class ListOfMovies (
    val Search : List<Search>,
    val totalResults : Int,
    val Response : String,
    val Error : String
)

data class Ratings (
    val source : String,
    val value : String
)

data class MovieInfo (
    val Title : String,
    val Year : Int,
    val Rated : String,
    val Released : String,
    val Runtime : String,
    val Genre : String,
    val Director : String,
    val Poster : String,
    val Writer : String,
    val Actors : String,
    val Plot : String,
    val Language : String,
    val Country : String,
    val Awards : String,
    val Ratings : List<Ratings>,
    val Metascore : String,
    val imdbRating : String,
    val imdbVotes : String,
    val imdbID : String,
    val Type : String,
    val DVD : String,
    val BoxOffice : String,
    val Production : String,
    val Website : String,
    val Response : String
)

enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}

data class ResultWrapper<out T>(val status: Status, val data: T?, val message: String?){
    companion object{
        fun <T> success(data: T?):ResultWrapper<T>{
            return ResultWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null):ResultWrapper<T>{
            return ResultWrapper(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?=null):ResultWrapper<T>{
            return ResultWrapper(Status.LOADING, data, null)
        }
    }
}

@BindingAdapter("imageURL")
fun setImage(view: ImageView, url: String){
    url.let {
        if(!it.isNullOrBlank()){
            Picasso.get().load(url).into(view)
        }
    }
}

@BindingAdapter("itemPosition", "onRowClick")
fun getRowClick(view: ConstraintLayout, itemPosition: Int, onRowClick: RowClickListener){
    view.setOnClickListener{
        onRowClick.onRowClick(itemPosition)
    }
}