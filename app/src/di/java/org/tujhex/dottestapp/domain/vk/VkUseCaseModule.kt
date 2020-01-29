package org.tujhex.dottestapp.domain.vk

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse
import org.tujhex.dottestapp.core.data.net.vk.VkApiService
import org.tujhex.dottestapp.domain.cases.vk.profile.GetUserProfileUseCase
import org.tujhex.dottestapp.domain.cases.vk.profile.RefreshUserProfileUseCase
import org.tujhex.dottestapp.domain.cases.vk.token.FetchVkTokenUseCase
import org.tujhex.dottestapp.domain.cases.vk.token.StoreVkTokenUseCase
import org.tujhex.dottestapp.domain.cases.vk.token.model.AuthToken

/**
 * @author tujhex
 * since 26.01.20
 */
@Module(includes = [VkCacheModule::class])
class VkUseCaseModule {


    @Provides
    @Reusable
    fun provideFetchVkToken(vkTokenCacheStorage: CacheStorage<AuthToken>): FetchVkTokenUseCase {
        return FetchVkTokenUseCase.Impl(vkTokenCacheStorage)
    }

    @Provides
    @Reusable
    fun provideStoreVkToken(vkTokenCacheStorage: CacheStorage<AuthToken>): StoreVkTokenUseCase {
        return StoreVkTokenUseCase.Impl(vkTokenCacheStorage)
    }

    @Provides
    @Reusable
    fun provideFetchVkProfileUseCase(
        vkApiService: VkApiService,
        cacheStorage: CacheStorage.Reactive<VkProfileResponse>,
        fetchVkTokenUseCase: FetchVkTokenUseCase
    ): RefreshUserProfileUseCase {
        return RefreshUserProfileUseCase.Impl(vkApiService, cacheStorage, fetchVkTokenUseCase)
    }

    @Provides
    @Reusable
    fun provideGetVkProfileUseCase(cacheStorage: CacheStorage.Reactive<VkProfileResponse>): GetUserProfileUseCase {
        return GetUserProfileUseCase.Impl(cacheStorage)
    }

}