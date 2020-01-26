package org.tujhex.dottestapp.domain.vk.cases.profile

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import org.tujhex.dottestapp.core.data.net.vk.VkApiService
import org.tujhex.dottestapp.domain.vk.model.VkProfileEntity

/**
 * @author tujhex
 * since 27.01.20
 */
interface FetchUserProfileUseCase {
    fun fetch(): Single<VkProfileEntity>

    class Impl(private val vkApiService: VkApiService) : FetchUserProfileUseCase {
        override fun fetch(): Single<VkProfileEntity> {
            return vkApiService
                .getInfo()
                .observeOn(Schedulers.io())
                .map { response ->
                    return@map when (response) {
                        is VkResponse.Profile -> VkProfileEntity.Profile(response.response)
                        is VkResponse.Error -> VkProfileEntity.Error(response.error)
                    }
                }
        }
    }
}