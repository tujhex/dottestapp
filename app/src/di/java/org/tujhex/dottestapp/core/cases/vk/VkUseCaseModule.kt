package org.tujhex.dottestapp.core.cases.vk

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.tujhex.dottestapp.core.data.vk.VkTokenCacheStorage

/**
 * @author tujhex
 * since 26.01.20
 */
@Module
class VkUseCaseModule {
    @Provides
    @Reusable
    fun provideVkTokenStorage(): VkTokenCacheStorage {
        return VkTokenCacheStorage.Impl()
    }

    @Provides
    @Reusable
    fun provideFetchVkToken(vkTokenCacheStorage: VkTokenCacheStorage): FetchVkTokenUseCase {
        return FetchVkTokenUseCase.Impl(vkTokenCacheStorage)
    }

    @Provides
    @Reusable
    fun provideStoreVkToken(vkTokenCacheStorage: VkTokenCacheStorage): StoreVkTokenUseCase {
        return StoreVkTokenUseCase.Impl(vkTokenCacheStorage)
    }
}