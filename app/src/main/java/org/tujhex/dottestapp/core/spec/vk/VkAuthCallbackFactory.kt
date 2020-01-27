package org.tujhex.dottestapp.core.spec.vk

import com.vk.api.sdk.auth.VKAuthCallback
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.domain.vk.cases.token.StoreVkTokenUseCase
import org.tujhex.dottestapp.core.data.net.vk.VkApiService
import org.tujhex.dottestapp.domain.vk.cases.profile.RefreshUserProfileUseCase
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 27.01.20
 */
interface VkAuthCallbackFactory {
    fun create(compositeDisposable: CompositeDisposable): VKAuthCallback

    class Impl(
        private val navigator: Navigator,
        private val messageUtils: MessageUtils,
        private val refreshUserProfileUseCase: RefreshUserProfileUseCase,
        private val storeVkTokenUseCase: StoreVkTokenUseCase
    ) : VkAuthCallbackFactory {
        override fun create(compositeDisposable: CompositeDisposable): VKAuthCallback {
            return VkAuthCallbackImpl(
                messageUtils,
                navigator,
                storeVkTokenUseCase,
                refreshUserProfileUseCase,
                compositeDisposable
            )
        }
    }
}