package org.tujhex.dottestapp.core.cases.vk

import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.vk.VkTokenCacheStorage

/**
 * @author tujhex
 * since 26.01.20
 */
interface FetchVkTokenUseCase {
    fun fetchToken(): Single<VKAccessToken>

    class Impl(private val vkTokenCacheStorage: VkTokenCacheStorage) : FetchVkTokenUseCase {
        override fun fetchToken(): Single<VKAccessToken> {
            return Single.fromCallable {
                vkTokenCacheStorage.fetch()
                    ?.let { it }
                    ?: throw NoSuchElementException("Token not present!")
            }.subscribeOn(Schedulers.io())
        }

    }
}