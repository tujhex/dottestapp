package org.tujhex.dottestapp.core.data.net.vk

import io.reactivex.Single
import org.tujhex.dottestapp.core.data.dto.vk.VkResponse
import org.tujhex.dottestapp.core.data.net.vk.json.VkJsonNames
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author tujhex
 * since 27.01.20
 */
interface VkApi {

    @GET("/method/users.get")
    fun getProfileInfo(@Query(VkJsonNames.ownerId) ownerId: Int, @Query(VkJsonNames.fields) fields: Array<String>): Single<VkResponse>

    @GET("/method/users.get")
    fun getUser(): Single<VkResponse>
}