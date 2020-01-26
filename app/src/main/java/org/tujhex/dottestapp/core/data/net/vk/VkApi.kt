package org.tujhex.dottestapp.core.data.net.vk

import io.reactivex.Single
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import retrofit2.http.GET

/**
 * @author tujhex
 * since 27.01.20
 */
interface VkApi {

    @GET("/method/account.getProfileInfo")
    fun getProfileInfo(): Single<VkResponse>
}