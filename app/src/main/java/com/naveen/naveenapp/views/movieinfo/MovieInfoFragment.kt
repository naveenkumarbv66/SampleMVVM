package com.naveen.naveenapp.views.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.naveen.naveenapp.R
import com.naveen.naveenapp.databinding.MovieInfoFragmentBinding
import com.naveen.naveenapp.network.MovieInfo

class MovieInfoFragment() : Fragment() {

    lateinit var movieInfoObject : MovieInfo

    lateinit var binding : MovieInfoFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_info_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.movieInfoObject = movieInfoObject
    }

}