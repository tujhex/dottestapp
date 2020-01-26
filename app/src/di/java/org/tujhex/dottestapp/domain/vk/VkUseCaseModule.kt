package org.tujhex.dottestapp.domain.vk

import com.vk.api.sdk.auth.VKAccessToken
import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.data.cache.vk.VkTokenCacheStorage
import org.tujhex.dottestapp.domain.vk.cases.token.FetchVkTokenUseCase
import org.tujhex.dottestapp.domain.vk.cases.token.StoreVkTokenUseCase

/**
 * @author tujhex
 * since 26.01.20
 */
@Module
class VkUseCaseModule {

    @Provides
    @Reusable
    fun provideVkTokenStorage(): CacheStorage<VKAccessToken> {
        return CacheStorage.ReactiveImpl(VkTokenCacheStorage.Impl())
    }

    @Provides
    @Reusable
    fun provideReactiveVkTokenStorage(cacheStorage: CacheStorage<VKAccessToken>): CacheStorage.Reactive<VKAccessToken> {
        return CacheStorage.ReactiveImpl(cacheStorage)
    }

    @Provides
    @Reusable
    fun provideFetchVkToken(vkTokenCacheStorage: CacheStorage.Reactive<VKAccessToken>): FetchVkTokenUseCase {
        return FetchVkTokenUseCase.Impl(vkTokenCacheStorage)
    }

    @Provides
    @Reusable
    fun provideStoreVkToken(vkTokenCacheStorage: CacheStorage.Reactive<VKAccessToken>): StoreVkTokenUseCase {
        return StoreVkTokenUseCase.Impl(vkTokenCacheStorage)
    }
}