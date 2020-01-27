package org.tujhex.dottestapp.domain.cases.github

import io.reactivex.Single
import org.tujhex.dottestapp.core.data.net.github.GithubApiService
import org.tujhex.dottestapp.core.data.net.github.json.GithubUserSearchResponse

/**
 * @author tujhex
 * since 27.01.20
 */
interface GithubSearchUserUseCase {
    fun searchUser(query: String, page: Int): Single<GithubUserSearchResponse>

    class Impl(private val githubApiService: GithubApiService) : GithubSearchUserUseCase {
        override fun searchUser(query: String, page: Int): Single<GithubUserSearchResponse> {
            return githubApiService.searchUsers(query, page)
        }
    }
}