package org.tujhex.dottestapp.login.model

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthCallback
import io.reactivex.disposables.CompositeDisposable
import org.tujhex.dottestapp.core.spec.vk.VkAuthCallbackFactory
import org.tujhex.dottestapp.login.navigation.VkLoginRouter

/**
 * @author tujhex
 * since 26.01.20
 */
class LoginViewModel(
    private val vkLoginRouter: VkLoginRouter,
    callbackFactory: VkAuthCallbackFactory
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val callback: VKAuthCallback = callbackFactory.create(compositeDisposable)

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