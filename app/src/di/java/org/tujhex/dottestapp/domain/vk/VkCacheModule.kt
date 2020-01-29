package org.tujhex.dottestapp.domain.vk

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse
import org.tujhex.dottestapp.domain.cases.vk.token.model.AuthToken

/**
 * @author tujhex
 * since 27.01.20
 */
@Module
class VkCacheModule {
    @Provides
    @Reusable
    fun provideVkTokenStorage(): CacheStorage<AuthToken> {
        return CacheStorage.ReactiveImpl(CacheStorage.Impl())
    }

    @Provides
    @Reusable
    fun provideReactiveVkTokenStorage(): CacheStorage.Reactive<VkProfileResponse> {
        return CacheStorage.ReactiveImpl(CacheStorage.Impl())
    }
}