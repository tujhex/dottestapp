package org.tujhex.dottestapp.core.data.net.github

import io.reactivex.Single
import org.tujhex.dottestapp.core.data.net.github.json.GithubUserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author tujhex
 * since 27.01.20
 */
interface GithubApi {

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String, @Query("page") page: Int): Single<GithubUserSearchResponse>
}