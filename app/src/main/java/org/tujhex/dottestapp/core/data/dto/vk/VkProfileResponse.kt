package org.tujhex.dottestapp.core.data.dto.vk

import com.google.gson.annotations.SerializedName
import org.tujhex.dottestapp.core.data.net.vk.json.VkJsonNames

/**
 * @author tujhex
 * since 27.01.20
 */
data class VkProfileResponse(
    @SerializedName(VkJsonNames.profileFirstName) val firstName: String,
    @SerializedName(VkJsonNames.profileLastName) val lastName: String
)