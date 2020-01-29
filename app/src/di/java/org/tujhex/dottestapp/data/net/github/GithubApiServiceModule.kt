package org.tujhex.dottestapp.data.net.github

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import org.tujhex.dottestapp.core.data.net.github.GithubApiService
import org.tujhex.dottestapp.data.net.GsonModule
import org.tujhex.dottestapp.main.MainScope
import org.tujhex.dottestapp.net.interceptor.InterceptorsModule

/**
 * @author tujhex
 * since 29.01.20
 */
@Module(includes = [InterceptorsModule::class, GsonModule::class])
class GithubApiServiceModule {
    @Provides
    @MainScope
    fun provideGithubApiService(
        loggingInterceptor: HttpLoggingInterceptor,
        gson: Gson
    ): GithubApiService {
        return GithubApiService.Impl(
            loggingInterceptor,
            gson
        )
    }
}