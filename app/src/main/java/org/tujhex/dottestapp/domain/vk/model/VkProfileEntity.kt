package org.tujhex.dottestapp.domain.vk.model

import org.tujhex.dottestapp.core.data.dto.vk.VkError
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse

/**
 * @author tujhex
 * since 27.01.20
 */
sealed class VkProfileEntity {
    data class Profile(val dto: VkProfileResponse) : VkProfileEntity()
    data class Error(val dto: VkError) : VkProfileEntity()
}