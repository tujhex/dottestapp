package org.tujhex.dottestapp.core.data.cache.vk

import com.vk.api.sdk.auth.VKAccessToken
import org.tujhex.dottestapp.core.data.cache.CacheStorage
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * @author tujhex
 * since 26.01.20
 */
interface VkTokenCacheStorage : CacheStorage<VKAccessToken> {

    class Impl : VkTokenCacheStorage {
        private val lock = ReentrantReadWriteLock()
        private var tokenCache: VKAccessToken? = null

        override fun store(data: VKAccessToken) {
            lock.write { tokenCache = data }
        }

        override fun fetch(): VKAccessToken? {
            return lock.read { tokenCache }
        }
    }

}