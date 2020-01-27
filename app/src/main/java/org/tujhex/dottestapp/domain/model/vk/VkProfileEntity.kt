package org.tujhex.dottestapp.domain.model.vk

import org.tujhex.dottestapp.core.data.dto.vk.VkErrorDto
import org.tujhex.dottestapp.core.data.dto.vk.VkProfileResponse

/**
 * @author tujhex
 * since 27.01.20
 */
sealed class VkProfileEntity {
    data class Profile(val dto: VkProfileResponse) : VkProfileEntity()
    data class Error(val dto: VkErrorDto) : VkProfileEntity()
}