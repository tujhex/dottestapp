package org.tujhex.dottestapp.login.model

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import org.tujhex.dottestapp.login.navigation.VkLoginRouter

/**
 * @author tujhex
 * since 26.01.20
 */
class LoginViewModel(private val vkLoginRouter: VkLoginRouter) : ViewModel() {

    fun loginVK() {
        vkLoginRouter.login()
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Log.d("TEST", token.toString())
            }

            override fun onLoginFailed(errorCode: Int) {
                Log.d("TEST", "Error: $errorCode")
            }
        }
        return (data != null && VK.onActivityResult(requestCode, resultCode, data, callback))
    }
}