package org.tujhex.dottestapp.login.navigation

import androidx.fragment.app.Fragment
import org.tujhex.dottestapp.login.LoginFragment
import org.tujhex.navigation.Screen

/**
 * @author tujhex
 * since 21.01.20
 */
class LoginScreen : Screen {
    override fun key(): String = "LoginScreen"


    override fun target(): Fragment = LoginFragment()
}