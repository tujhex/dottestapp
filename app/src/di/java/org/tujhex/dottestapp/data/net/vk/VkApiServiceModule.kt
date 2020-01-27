package org.tujhex.dottestapp.data.net.vk

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import org.tujhex.dottestapp.core.data.net.vk.VkApiService
import org.tujhex.dottestapp.core.net.interceptor.AccessTokenInterceptor
import org.tujhex.dottestapp.data.net.GsonModule
import org.tujhex.dottestapp.main.MainScope
import org.tujhex.dottestapp.net.interceptor.InterceptorsModule

/**
 * @author tujhex
 * since 27.01.20
 */
@Module(includes = [InterceptorsModule::class, GsonModule::class])
class VkApiServiceModule {


    @Provides
    @MainScope
    fun provideVkApiService(
        accessTokenInterceptor: AccessTokenInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        gson: Gson
    ): VkApiService {
        return VkApiService.Impl(
            arrayOf(accessTokenInterceptor, httpLoggingInterceptor),
            gson
        )
    }

}