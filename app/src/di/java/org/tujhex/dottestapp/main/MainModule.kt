package org.tujhex.dottestapp.main

import dagger.Module
import dagger.Provides
import org.tujhex.dottestapp.login.model.LoginProviderFactory
import org.tujhex.dottestapp.login.navigation.VkLoginRouter
import org.tujhex.dottestapp.main.model.MainProviderFactory
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 *
 * FIXME: dep-s should be provided by interface ViewModelProvider.Factory, but I encounter some error with @Named qualifier
 */
@Module
class MainModule {


    @MainScope
    @Provides
    fun provideMainProviderFactory(navigator: Navigator): MainProviderFactory {
        return MainProviderFactory(navigator)
    }


    @MainScope
    @Provides
    fun provideLoginProviderFactory(vkLoginRouter: VkLoginRouter): LoginProviderFactory {
        return LoginProviderFactory(vkLoginRouter)
    }
}