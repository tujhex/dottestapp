package org.tujhex.navigation

import androidx.fragment.app.Fragment

/**
 * @author tujhex
 * since 21.01.20
 */
interface Screen {
    fun key(): String
    fun target(): Fragment
}