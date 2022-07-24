package com.mubin.unsplashgallery.application

import android.app.Application
import com.mubin.unsplashgallery.helper.Session
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application (){


    override fun onCreate() {
        super.onCreate()
        Session.init(this)
    }

}