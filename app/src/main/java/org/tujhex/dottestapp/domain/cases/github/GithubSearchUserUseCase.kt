package org.tujhex.dottestapp.domain.cases.github

import io.reactivex.Single
import org.tujhex.dottestapp.core.data.net.github.GithubApiService
import org.tujhex.dottestapp.domain.cases.github.model.GithubSearchQueryModel
import org.tujhex.dottestapp.github.model.GithubUserUiModel

/**
 * @author tujhex
 * since 27.01.20
 */
interface GithubSearchUserUseCase {
    fun searchUser(query: String, page: Int): Single<GithubSearchQueryModel>

    class Impl(private val githubApiService: GithubApiService) : GithubSearchUserUseCase {
        override fun searchUser(query: String, page: Int): Single<GithubSearchQueryModel> {
            return githubApiService.searchUsers(query, page)
                .map {
                    GithubSearchQueryModel(it.users.map { user ->
                        GithubUserUiModel(
                            user.id,
                            user.login,
                            user.avatarUrl
                        )
                    }, it.totalCount.toInt())
                }

        }
    }
}