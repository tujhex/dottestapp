package org.tujhex.dottestapp.login.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.tujhex.dottestapp.login.navigation.VkLoginRouter

/**
 * @author tujhex
 * since 26.01.20
 */
class LoginProviderFactory(private val vkLoginRouter: VkLoginRouter) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(vkLoginRouter) as T
            else -> throw IllegalArgumentException("Class $modelClass not acceptable by this factory!")
        }
    }
}