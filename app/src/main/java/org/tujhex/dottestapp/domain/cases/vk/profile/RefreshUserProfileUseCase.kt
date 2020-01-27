package org.tujhex.dottestapp.domain.cases.vk.profile

import com.vk.api.sdk.auth.VKAccessToken
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import org.tujhex.dottestapp.core.data.net.vk.VkApiService
import org.tujhex.dottestapp.core.data.net.vk.json.VkJsonNames
import org.tujhex.dottestapp.domain.model.vk.VkProfileEntity

/**
 * @author tujhex
 * since 27.01.20
 */
interface RefreshUserProfileUseCase {
    fun fetchProfile(): Single<VkProfileEntity>

    class Impl(
        private val vkApiService: VkApiService,
        private val profileCacheStorage: CacheStorage.Reactive<VkProfileResponse>,
        private val tokenCacheStorage: CacheStorage<VKAccessToken>
    ) : RefreshUserProfileUseCase {
        override fun fetchProfile(): Single<VkProfileEntity> {
            return Single.fromCallable {
                tokenCacheStorage.fetch() ?: throw IllegalStateException()
            }
                .subscribeOn(Schedulers.io())
                .flatMap {
                    if (it.userId == null) {
                        return@flatMap Single.error<VkResponse>(IllegalArgumentException())
                    }
                    vkApiService.getInfo(it.userId!!, arrayOf(VkJsonNames.userPhoto))
                }
                .map { response ->
                    return@map when (response) {
                        is VkResponse.Profile -> VkProfileEntity.Profile(response.response.first())
                        is VkResponse.Error -> VkProfileEntity.Error(response.errorDto)
                    }
                }.doOnSuccess {
                    if (it is VkProfileEntity.Profile) {
                        profileCacheStorage.store(it.dto)
                    }
                }
        }
    }
}