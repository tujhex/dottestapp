package org.tujhex.dottestapp.github.navigation

import androidx.fragment.app.Fragment
import org.tujhex.dottestapp.github.GithubSearchFragment
import org.tujhex.navigation.Screen

/**
 * @author tujhex
 * since 27.01.20
 */
class GithubSearchScreen:Screen {
    override fun target(): Fragment = GithubSearchFragment()

    override fun key(): String  = "GithubSearchScreen"
}