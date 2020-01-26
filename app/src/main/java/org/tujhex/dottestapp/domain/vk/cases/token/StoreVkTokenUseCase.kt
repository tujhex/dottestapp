package org.tujhex.dottestapp.domain.vk.cases.token

import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.cache.CacheStorage

/**
 * @author tujhex
 * since 26.01.20
 */
interface StoreVkTokenUseCase {
    fun storeToken(token: VKAccessToken): Completable

    class Impl(private val vkTokenCacheStorage: CacheStorage<VKAccessToken>) :
        StoreVkTokenUseCase {
        override fun storeToken(token: VKAccessToken): Completable {
            return Completable.fromAction { vkTokenCacheStorage.store(token) }
                .subscribeOn(Schedulers.io())
        }

    }
}