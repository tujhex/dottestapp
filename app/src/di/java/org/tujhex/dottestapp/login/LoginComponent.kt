package org.tujhex.dottestapp.login

import dagger.Subcomponent

/**
 * @author tujhex
 * since 21.01.20
 */
@LoginScope
@Subcomponent
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
}