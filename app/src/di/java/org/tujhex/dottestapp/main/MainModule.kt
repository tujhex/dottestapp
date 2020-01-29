package org.tujhex.dottestapp.main

import dagger.Module
import dagger.Provides
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.spec.vk.VkAuthCallbackFactory
import org.tujhex.dottestapp.data.net.github.GithubApiServiceModule
import org.tujhex.dottestapp.data.net.vk.VkApiServiceModule
import org.tujhex.dottestapp.domain.cases.github.GithubSearchUserUseCase
import org.tujhex.dottestapp.domain.cases.vk.profile.GetUserProfileUseCase
import org.tujhex.dottestapp.domain.cases.vk.profile.RefreshUserProfileUseCase
import org.tujhex.dottestapp.domain.cases.vk.token.StoreVkTokenUseCase
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
@Module(includes = [VkApiServiceModule::class, GithubApiServiceModule::class])
class MainModule {


    @MainScope
    @Provides
    fun provideMainProviderFactory(
        navigator: Navigator,
        messageUtils: MessageUtils,
        getUserProfileUseCase: GetUserProfileUseCase,
        githubSearchUserUseCase: GithubSearchUserUseCase
    ): MainProviderFactory {
        return MainProviderFactory(
            navigator,
            messageUtils,
            getUserProfileUseCase,
            githubSearchUserUseCase
        )
    }

    @MainScope
    @Provides
    fun provideVkCallbackFactory(
        navigator: Navigator,
        messageUtils: MessageUtils,
        refreshUserProfileUseCase: RefreshUserProfileUseCase,
        storeVkTokenUseCase: StoreVkTokenUseCase
    ): VkAuthCallbackFactory {
        return VkAuthCallbackFactory.Impl(
            navigator,
            messageUtils,
            refreshUserProfileUseCase,
            storeVkTokenUseCase
        )
    }

    @MainScope
    @Provides
    fun provideLoginProviderFactory(
        vkLoginRouter: VkLoginRouter,
        callbackFactory: VkAuthCallbackFactory
    ): LoginProviderFactory {
        return LoginProviderFactory(vkLoginRouter, callbackFactory)
    }

}