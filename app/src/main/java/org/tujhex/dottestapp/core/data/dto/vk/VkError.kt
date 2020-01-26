package org.tujhex.dottestapp.core.data.dto.vk

import com.google.gson.annotations.SerializedName

/**
 * @author tujhex
 * since 27.01.20
 */
data class VkError(@SerializedName("error_msg") val message:String)