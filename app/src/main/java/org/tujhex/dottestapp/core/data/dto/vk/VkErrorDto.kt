package org.tujhex.dottestapp.core.data.dto.vk

import com.google.gson.annotations.SerializedName
import org.tujhex.dottestapp.core.data.net.vk.json.VkJsonNames

/**
 * @author tujhex
 * since 27.01.20
 */
data class VkErrorDto(@SerializedName(VkJsonNames.errorMessage) val message:String)