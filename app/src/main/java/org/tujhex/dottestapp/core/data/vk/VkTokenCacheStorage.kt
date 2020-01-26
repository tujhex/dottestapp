package org.tujhex.dottestapp.core.data.vk

import com.vk.api.sdk.auth.VKAccessToken
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * @author tujhex
 * since 26.01.20
 */
interface VkTokenCacheStorage {
    fun store(vkAccessToken: VKAccessToken)
    fun fetch(): VKAccessToken?

    class Impl : VkTokenCacheStorage {
        private val lock = ReentrantReadWriteLock()
        private var token: VKAccessToken? = null

        override fun store(vkAccessToken: VKAccessToken) {
            lock.write { token = vkAccessToken }
        }

        override fun fetch(): VKAccessToken? {
            return lock.read { token }
        }

    }
}