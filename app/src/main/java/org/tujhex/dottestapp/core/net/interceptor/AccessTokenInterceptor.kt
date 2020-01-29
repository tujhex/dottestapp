package org.tujhex.dottestapp.core.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.domain.cases.vk.token.model.AuthToken

/**
 * @author tujhex
 * since 27.01.20
 */
class AccessTokenInterceptor(private val cacheStorage: CacheStorage<AuthToken>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return when (val value = cacheStorage.fetch()) {
            is AuthToken.Vk -> {
                addVkToken(chain, value)
            }
            else -> chain.proceed(chain.request())
        }
    }

    private fun addVkToken(
        chain: Interceptor.Chain,
        value: AuthToken.Vk
    ): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(
                    chain.request()
                        .url
                        .newBuilder()
                        .addQueryParameter(TOKEN_QUERY_NAME, value.accessToken.accessToken)
                        .addQueryParameter(API_VERSION_NAME, "5.103")
                        .build()
                )
                .build()
        )
    }

    companion object {
        private const val TOKEN_QUERY_NAME = "access_token"
        private const val API_VERSION_NAME = "v"
    }
}