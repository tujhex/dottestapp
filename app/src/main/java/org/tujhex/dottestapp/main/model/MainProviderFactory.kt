package org.tujhex.dottestapp.main.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.tujhex.dottestapp.domain.vk.cases.profile.GetUserProfileUseCase
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class MainProviderFactory(
    private val navigator: Navigator,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> MainViewModel(navigator) as T
            DrawerViewModel::class.java -> DrawerViewModel(getUserProfileUseCase) as T
            else -> throw IllegalArgumentException("Class $modelClass not acceptable by this factory!")
        }
    }
}