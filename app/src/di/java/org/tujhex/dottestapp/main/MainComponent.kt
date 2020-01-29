package org.tujhex.dottestapp.main

import dagger.Subcomponent
import org.tujhex.dottestapp.core.DiComponent
import org.tujhex.dottestapp.domain.github.GithubModule
import org.tujhex.dottestapp.domain.vk.VkUseCaseModule
import org.tujhex.dottestapp.github.GithubComponent
import org.tujhex.dottestapp.login.LoginComponent


/**
 * @author tujhex
 * since 21.01.20
 */
@MainScope
@Subcomponent(modules = [NavigationModule::class, MainModule::class, VkUseCaseModule::class, GithubModule::class])
interface MainComponent : DiComponent {
    fun inject(activity: MainActivity)
    fun plus(): LoginComponent
    fun plusGithub(): GithubComponent
}