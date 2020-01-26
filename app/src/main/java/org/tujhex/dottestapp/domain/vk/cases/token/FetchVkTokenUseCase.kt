package org.tujhex.dottestapp.domain.vk.cases.token

import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.cache.CacheStorage

/**
 * @author tujhex
 * since 26.01.20
 */
interface FetchVkTokenUseCase {
    fun fetchToken(): Single<VKAccessToken>

    class Impl(private val vkTokenCacheStorage: CacheStorage<VKAccessToken>) :
        FetchVkTokenUseCase {
        override fun fetchToken(): Single<VKAccessToken> {
            return Single.fromCallable {
                vkTokenCacheStorage.fetch()
                    ?.let { it }
                    ?: throw NoSuchElementException("Token not present!")
            }.subscribeOn(Schedulers.io())
        }

    }
}