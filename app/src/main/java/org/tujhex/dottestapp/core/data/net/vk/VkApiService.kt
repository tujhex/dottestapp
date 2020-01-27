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
    fun getInfo(ownerId: Int, fields: Array<String>): Single<VkResponse>

    companion object {
        const val endpoint = "https://api.vk.com"
    }

    class Impl(interceptors: Array<Interceptor>, gson: Gson) :
        ApiService<VkApi>(endpoint, interceptors, gson), VkApiService {

        override fun getClazz(): Class<VkApi> = VkApi::class.java

        override fun getInfo(ownerId: Int, fields: Array<String>): Single<VkResponse> {
            return getApi()
                .getProfileInfo(ownerId, fields)
                .subscribeOn(Schedulers.io())
        }

    }
}