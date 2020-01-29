package org.tujhex.dottestapp.domain.cases.vk.token

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.domain.cases.vk.token.model.AuthToken

/**
 * @author tujhex
 * since 26.01.20
 */
interface FetchVkTokenUseCase {
    fun fetchToken(): Single<AuthToken>

    class Impl(private val vkTokenCacheStorage: CacheStorage<AuthToken>) :
        FetchVkTokenUseCase {
        override fun fetchToken(): Single<AuthToken> {
            return Single.fromCallable {
                vkTokenCacheStorage.fetch()
                    ?.let { it }
                    ?: throw NoSuchElementException("Token not present!")
            }.subscribeOn(Schedulers.io())
        }

    }
}