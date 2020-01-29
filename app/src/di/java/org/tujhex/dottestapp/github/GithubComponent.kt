package org.tujhex.dottestapp.github

import dagger.Subcomponent

/**
 * @author tujhex
 * since 21.01.20
 */
@GithubScope
@Subcomponent
interface GithubComponent {
    fun inject(fragment: GithubSearchFragment)
}