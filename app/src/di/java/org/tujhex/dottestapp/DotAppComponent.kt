package org.tujhex.dottestapp

import dagger.Component
import org.tujhex.dottestapp.main.MainComponent
import org.tujhex.dottestapp.main.NavigationModule

/**
 * @author tujhex
 * since 21.01.20
 */
@Component(modules = [DotAppModule::class])
interface DotAppComponent {
    fun plus(navigationModule: NavigationModule):MainComponent
}