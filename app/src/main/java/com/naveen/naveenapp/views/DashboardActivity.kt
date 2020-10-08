package com.naveen.naveenapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naveen.naveenapp.R
import com.naveen.naveenapp.views.ui.movielisting.MovieListingFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListingFragment.newInstance())
                .commitNow()
        }
    }
}