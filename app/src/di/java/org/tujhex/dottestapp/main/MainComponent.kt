package org.tujhex.dottestapp.main

import dagger.Subcomponent
import org.tujhex.dottestapp.login.LoginComponent


/**
 * @author tujhex
 * since 21.01.20
 */
@MainScope
@Subcomponent(modules = [NavigationModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
    fun plus(): LoginComponent
}