package org.tujhex.dottestapp.core.data.net

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author tujhex
 * since 27.01.20
 */
abstract class ApiService<T>(
    private val endpoint: String,
    private val interceptors: Array<Interceptor>,
    private val gson: Gson
) {

    private var apiCache: T? = null

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endpoint)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient
                    .Builder()
                    .apply {
                        for (interceptor in interceptors) {
                            addInterceptor(interceptor)
                        }
                    }.build()
            ).build()
    }


    protected fun getApi(): T {
        if (apiCache == null) {
            apiCache = getRetrofit().create(getClazz())
        }
        return apiCache!!
    }

    abstract fun getClazz(): Class<T>
}