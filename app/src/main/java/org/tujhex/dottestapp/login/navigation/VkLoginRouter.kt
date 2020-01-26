package org.tujhex.dottestapp.login.navigation

import androidx.fragment.app.FragmentActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

/**
 * @author tujhex
 * since 26.01.20
 */
interface VkLoginRouter {
    class Impl : VkLoginRouter {
        private var target: FragmentActivity? = null
        override fun login() {
            target?.let {
                VK.login(it, arrayListOf(VKScope.WALL))
            }
        }

        override fun attach(activity: FragmentActivity?) {
            target = activity
        }

        override fun detach() {
            target = null
        }
    }

    fun login()

    fun attach(activity: FragmentActivity?)

    fun detach()
}