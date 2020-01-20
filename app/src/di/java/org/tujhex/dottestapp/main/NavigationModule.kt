package org.tujhex.dottestapp.main

import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import org.tujhex.navigation.Navigator
import javax.inject.Singleton

/**
 * @author tujhex
 * since 21.01.20
 */
@Module
class NavigationModule(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {

    @Provides
    @MainScope
    fun provideNavigator(): Navigator {
        return Navigator.Impl(fragmentManager, containerId)
    }
}