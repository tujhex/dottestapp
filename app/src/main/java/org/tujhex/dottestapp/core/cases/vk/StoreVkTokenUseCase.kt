package org.tujhex.dottestapp.core.cases.vk

import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.vk.VkTokenCacheStorage

/**
 * @author tujhex
 * since 26.01.20
 */
interface StoreVkTokenUseCase {
    fun storeToken(token: VKAccessToken): Completable

    class Impl(private val vkTokenCacheStorage: VkTokenCacheStorage) : StoreVkTokenUseCase {
        override fun storeToken(token: VKAccessToken): Completable {
            return Completable.fromAction { vkTokenCacheStorage.store(token) }
                .subscribeOn(Schedulers.io())
        }

    }
}