package org.tujhex.dottestapp.main.model

import androidx.lifecycle.ViewModel
import org.tujhex.dottestapp.login.navigation.LoginScreen
import org.tujhex.navigation.Command
import org.tujhex.navigation.Navigator

/**
 * @author tujhex
 * since 26.01.20
 */
class MainViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun goToLogin() {
        navigator.navigate(
            Command.Forward(
                LoginScreen()
            )
        )
    }
}