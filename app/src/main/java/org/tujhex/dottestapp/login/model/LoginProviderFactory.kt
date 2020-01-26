package org.tujhex.dottestapp.login.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.cases.vk.StoreVkTokenUseCase
import org.tujhex.dottestapp.login.navigation.VkLoginRouter
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class LoginProviderFactory(
    private val vkLoginRouter: VkLoginRouter,
    private val messageUtils: MessageUtils,
    private val navigator: Navigator,
    private val storeVkTokenUseCase: StoreVkTokenUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(
                vkLoginRouter,
                navigator,
                messageUtils,
                storeVkTokenUseCase
            ) as T
            else -> throw IllegalArgumentException("Class $modelClass not acceptable by this factory!")
        }
    }
}