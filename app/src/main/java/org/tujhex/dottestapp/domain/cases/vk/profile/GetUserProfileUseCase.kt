package org.tujhex.dottestapp.domain.cases.vk.profile

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse

/**
 * @author tujhex
 * since 27.01.20
 */
interface GetUserProfileUseCase {
    fun getProfile(): Observable<VkProfileResponse>

    class Impl(private val cacheStorage: CacheStorage.Reactive<VkProfileResponse>) :
        GetUserProfileUseCase {
        override fun getProfile(): Observable<VkProfileResponse> {
            return cacheStorage.fetchReactive().subscribeOn(Schedulers.io())
        }

    }
}