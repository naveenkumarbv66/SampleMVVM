package com.naveen.naveenapp.adapter

import com.airbnb.epoxy.EpoxyController
import com.naveen.naveenapp.network.Search
import com.naveen.naveenapp.serachMovieRow

class SearchMovieEpoxyController : EpoxyController(){

    var searchMoviesList : List<Search> = emptyList()
    lateinit var onRowClick : RowClickListener

    override fun buildModels() {
        searchMoviesList.forEachIndexed { index, search ->
            serachMovieRow {
                id("id", index.toString())
                title(search.Title)
                movieImage(search.Poster)
                rowIndex(index)
                onRowClickListener(onRowClick)
            }
        }
    }
}

interface RowClickListener{
    fun onRowClick(itemPosition : Int)
}