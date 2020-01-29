package org.tujhex.dottestapp.core.data.net.github

import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import org.tujhex.dottestapp.core.data.net.ApiService
import org.tujhex.dottestapp.core.data.net.github.json.GithubUserSearchResponse

/**
 * @author tujhex
 * since 27.01.20
 */
interface GithubApiService {
    fun searchUsers(query: String, page: Int): Single<GithubUserSearchResponse>


    companion object {
        const val endpoint = "https://api.github.com"
    }

    class Impl(interceptor: Interceptor, gson: Gson) : GithubApiService, ApiService<GithubApi>(
        endpoint,
        arrayOf(interceptor),
        gson
    ) {
        override fun searchUsers(query: String, page: Int): Single<GithubUserSearchResponse> {
            return getApi()
                .searchUsers(query, page, 30)
                .subscribeOn(Schedulers.io())
        }

        override fun getClazz(): Class<GithubApi> = GithubApi::class.java
    }
}