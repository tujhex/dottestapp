package org.tujhex.dottestapp.domain.github

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.tujhex.dottestapp.core.data.net.github.GithubApiService
import org.tujhex.dottestapp.domain.cases.github.GithubSearchUserUseCase

/**
 * @author tujhex
 * since 29.01.20
 */
@Module
class GithubModule {

    @Provides
    @Reusable
    fun provideGithubSearchUseCase(githubApiService: GithubApiService): GithubSearchUserUseCase {
        return GithubSearchUserUseCase.Impl(githubApiService)
    }
}