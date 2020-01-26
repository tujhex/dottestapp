package org.tujhex.dottestapp.main.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vk.api.sdk.auth.VKAccessToken
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class MainProviderFactory(
    private val navigator: Navigator
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(navigator) as T
            else -> throw IllegalArgumentException("Class $modelClass not acceptable by this factory!")
        }
    }
}