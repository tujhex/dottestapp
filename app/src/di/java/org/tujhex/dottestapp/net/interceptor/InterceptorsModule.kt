package org.tujhex.dottestapp.net.interceptor

import com.vk.api.sdk.auth.VKAccessToken
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import org.tujhex.dottestapp.core.net.interceptor.AccessTokenInterceptor
import org.tujhex.dottestapp.main.MainScope
import org.tujhex.navigation.BuildConfig

/**
 * @author tujhex
 * since 27.01.20
 */
@Module
class InterceptorsModule {
    @Provides
    @MainScope
    fun provideAccessTokenInterceptor(cacheStorage: CacheStorage<VKAccessToken>): AccessTokenInterceptor {
        return AccessTokenInterceptor(cacheStorage)
    }

    @Provides
    @MainScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            val loggingLevel =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            setLevel(loggingLevel)
        }
    }
}