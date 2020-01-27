package org.tujhex.dottestapp.core.data.cache

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

/**
 * @author tujhex
 * since 27.01.20
 */
interface CacheStorage<T> {
    fun fetch(): T?
    fun store(data: T)

    interface Reactive<T> : CacheStorage<T> {
        fun fetchReactive(): Observable<T>
    }

    class Impl<T> : CacheStorage<T> {
        private val lock = ReentrantReadWriteLock()
        private var tokenCache: T? = null

        override fun store(data: T) {
            lock.write { tokenCache = data }
        }

        override fun fetch(): T? {
            return lock.read { tokenCache }
        }

    }

    class ReactiveImpl<T>(private val cacheStorage: CacheStorage<T>) :
        Reactive<T>, CacheStorage<T> by cacheStorage {
        private var reactive: Subject<T>? = null

        override fun fetchReactive(): Observable<T> {
            checkReactive()
            return reactive!!
        }

        private fun checkReactive(): Boolean {
            synchronized(this.javaClass) {
                if (reactive == null) {
                    reactive = BehaviorSubject.create<T>().apply { fetch()?.let { onNext(it) } }
                    return true
                }
                return false
            }
        }

        override fun store(data: T) {
            cacheStorage.store(data)
            if (!checkReactive()) {
                reactive?.onNext(data)
            }
        }
    }
}