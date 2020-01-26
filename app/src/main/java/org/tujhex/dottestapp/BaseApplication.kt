package org.tujhex.dottestapp

import android.app.Application
import org.tujhex.dottestapp.core.HasDiComponent

/**
 * @author tujhex
 * since 21.01.20
 */
class BaseApplication : Application(), HasDiComponent<DotAppComponent> {
    override fun getComponent(): DotAppComponent = appComponent

    private lateinit var appComponent: DotAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerDotAppComponent.create()

    }
}