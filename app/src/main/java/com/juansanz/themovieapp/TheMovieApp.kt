package com.juansanz.themovieapp

import android.app.Application
import com.juansanz.themovieapp.di.initDI

class TheMovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}
