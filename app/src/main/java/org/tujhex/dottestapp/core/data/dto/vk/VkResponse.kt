package org.tujhex.dottestapp.core.data.dto.vk

import com.google.gson.annotations.SerializedName

/**
 * @author tujhex
 * since 27.01.20
 */
sealed class VkResponse {
    data class Error(@SerializedName("error") val error: VkError) : VkResponse()
    data class Profile(@SerializedName("response") val response: VkProfileResponse) : VkResponse()
}