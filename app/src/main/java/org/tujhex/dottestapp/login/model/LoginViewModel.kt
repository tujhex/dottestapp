package org.tujhex.dottestapp.login.model

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthCallback
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.cases.vk.StoreVkTokenUseCase
import org.tujhex.dottestapp.core.vk.VkAuthCallbackImpl
import org.tujhex.dottestapp.login.navigation.VkLoginRouter
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class LoginViewModel(
    private val vkLoginRouter: VkLoginRouter,
    navigator: Navigator,
    messageUtils: MessageUtils,
    storeVkTokenUseCase: StoreVkTokenUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val callback: VKAuthCallback =
        VkAuthCallbackImpl(messageUtils, navigator, storeVkTokenUseCase, compositeDisposable)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loginVK() {
        vkLoginRouter.login()
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        return (data != null && VK.onActivityResult(requestCode, resultCode, data, callback))
    }
}