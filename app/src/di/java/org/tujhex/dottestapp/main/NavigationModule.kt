package org.tujhex.dottestapp.main

import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import org.tujhex.dottestapp.login.navigation.VkLoginRouter
import org.tujhex.navigation.Navigator

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

    @Provides
    @MainScope
    fun provideVkRouter(): VkLoginRouter {
        return VkLoginRouter.Impl()
    }
}