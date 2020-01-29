package org.tujhex.dottestapp.domain.cases.vk.token.model

import com.vk.api.sdk.auth.VKAccessToken

/**
 * @author tujhex
 * since 29.01.20
 */
sealed class AuthToken {
    data class Vk(val accessToken: VKAccessToken) : AuthToken()
}