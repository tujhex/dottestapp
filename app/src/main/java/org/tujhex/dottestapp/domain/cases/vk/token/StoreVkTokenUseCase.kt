package org.tujhex.dottestapp.domain.cases.vk.token

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.domain.cases.vk.token.model.AuthToken

/**
 * @author tujhex
 * since 26.01.20
 */
interface StoreVkTokenUseCase {
    fun storeToken(token: AuthToken): Completable

    class Impl(private val vkTokenCacheStorage: CacheStorage<AuthToken>) :
        StoreVkTokenUseCase {
        override fun storeToken(token: AuthToken): Completable {
            return Completable.fromAction { vkTokenCacheStorage.store(token) }
                .subscribeOn(Schedulers.io())
        }

    }
}