package org.tujhex.dottestapp.core

/**
 * @author tujhex
 * since 26.01.20
 */
interface HasDiComponent<T : DiComponent> {
    fun getComponent(): T
}