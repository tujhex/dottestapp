package org.tujhex.dottestapp.core.net.interceptor

import com.vk.api.sdk.auth.VKAccessToken
import okhttp3.Interceptor
import okhttp3.Response
import org.tujhex.dottestapp.core.data.cache.CacheStorage

/**
 * @author tujhex
 * since 27.01.20
 */
class AccessTokenInterceptor(private val cacheStorage: CacheStorage<VKAccessToken>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(
                    chain.request()
                        .url
                        .newBuilder()
                        .addQueryParameter(TOKEN_QUERY_NAME, cacheStorage.fetch()?.accessToken)
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