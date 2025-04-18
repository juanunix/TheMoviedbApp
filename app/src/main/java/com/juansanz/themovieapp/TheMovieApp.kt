package com.juansanz.themovieapp

import android.app.Application
import android.content.Context

class TheMovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: TheMovieApp

        fun getContext(): Context = instance.applicationContext
    }
}
