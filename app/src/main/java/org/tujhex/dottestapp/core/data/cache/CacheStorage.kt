package org.tujhex.dottestapp.core.data.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author tujhex
 * since 27.01.20
 */
interface CacheStorage<T> {
    fun fetch(): T?
    fun store(data: T)

    interface Reactive<T> : CacheStorage<T> {
        fun fetchReactive(): LiveData<T>
    }

    class ReactiveImpl<T>(private val cacheStorage: CacheStorage<T>) :
        Reactive<T>, CacheStorage<T> by cacheStorage {
        private var reactive: MutableLiveData<T>? = null
        override fun fetchReactive(): LiveData<T> {
            checkReactive()
            return reactive!!
        }

        private fun checkReactive() {
            if (reactive == null) {
                reactive = MutableLiveData<T>().apply { value = fetch() }
            }
        }

        override fun store(data: T) {
            checkReactive()
            cacheStorage.store(data)
            reactive?.value = data
        }
    }
}