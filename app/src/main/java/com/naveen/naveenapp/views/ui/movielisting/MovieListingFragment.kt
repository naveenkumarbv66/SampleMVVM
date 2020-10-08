package com.naveen.naveenapp.views.ui.movielisting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.naveen.naveenapp.R
import com.naveen.naveenapp.adapter.RowClickListener
import com.naveen.naveenapp.adapter.SearchMovieEpoxyController
import com.naveen.naveenapp.databinding.MoviewListingFragmentBinding
import com.naveen.naveenapp.views.movieinfo.MovieInfoFragment


class MovieListingFragment : Fragment(), SearchView.OnQueryTextListener {

    companion object {
        fun newInstance() = MovieListingFragment()
    }

    private lateinit var viewModel: MovieListingViewModel
    lateinit var epoxyControler: SearchMovieEpoxyController

    lateinit var binding : MoviewListingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.moview_listing_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieListingViewModel::class.java)

        epoxyControler = SearchMovieEpoxyController()
        binding.naveenRecy.adapter = epoxyControler.adapter

        epoxyControler.onRowClick = object : RowClickListener{
            override fun onRowClick(itemPosition: Int) {
                viewModel.getMovieInfo(epoxyControler.searchMoviesList[itemPosition].imdbID)
            }
        }

        viewModel.searchMovieList.observe(this, Observer {
            if(viewLifecycleOwner.lifecycle.currentState== Lifecycle.State.RESUMED){
                if (!it.Search.isNullOrEmpty()) {
                    epoxyControler.searchMoviesList = it.Search
                    epoxyControler.requestModelBuild()
                } else {
                    Toast.makeText(context, "No data found", Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.movieInfo.observe(this, Observer {
            if(viewLifecycleOwner.lifecycle.currentState==Lifecycle.State.RESUMED){
                val fragment2 = MovieInfoFragment()
                fragment2.movieInfoObject =it
                val fragmentManager: FragmentManager? = fragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                fragmentTransaction.replace(R.id.container, fragment2)
                fragmentTransaction.addToBackStack("movieInfo")
                fragmentTransaction.commit()
            }
        })

        viewModel.initSerachObserver("Marvel")

        binding.searchView.isIconified = false
        binding.searchView.setOnQueryTextListener(this)
        binding.searchView.clearFocus()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.initSerachObserver(query ?: "non")
        binding.searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}