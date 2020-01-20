package org.tujhex.dottestapp

import android.app.Application

/**
 * @author tujhex
 * since 21.01.20
 */
class BaseApplication : Application() {

    lateinit var appComponent: DotAppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDotAppComponent.create()

    }
}