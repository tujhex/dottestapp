package org.tujhex.dottestapp.core.data.dto.vk

import com.google.gson.annotations.SerializedName
import org.tujhex.dottestapp.core.data.net.vk.json.VkJsonNames

/**
 * @author tujhex
 * since 27.01.20
 */
sealed class VkResponse {
    data class Error(@SerializedName(VkJsonNames.error) val errorDto: VkErrorDto) : VkResponse()
    data class Profile(@SerializedName(VkJsonNames.response) val response: List<VkProfileResponse>) :
        VkResponse()

}