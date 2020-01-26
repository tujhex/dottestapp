package org.tujhex.dottestapp.core.data.net.vk

import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import org.tujhex.dottestapp.core.data.net.ApiService

/**
 * @author tujhex
 * since 27.01.20
 */
interface VkApiService {
    fun getInfo(): Single<VkResponse>


    class Impl(endpoint: String, interceptors: Array<Interceptor>, gson: Gson) :
        ApiService<VkApi>(endpoint, interceptors, gson), VkApiService {
        override fun getClazz(): Class<VkApi> = VkApi::class.java

        override fun getInfo(): Single<VkResponse> {
            return getApi()
                .getProfileInfo()
                .subscribeOn(Schedulers.io())
        }
    }
}