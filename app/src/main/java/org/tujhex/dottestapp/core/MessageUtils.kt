package org.tujhex.dottestapp.core

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * @author tujhex
 * since 26.01.20
 */
interface MessageUtils {
    fun showMessage(message: CharSequence)

    fun showMessage(messageRes: Int)
    fun showError(error: Throwable)

    class Impl(private val context: Context) : MessageUtils {
        override fun showError(error: Throwable) {
            Log.e("MessageUtils", error.localizedMessage, error)
            error.localizedMessage
                ?.let { showMessage(it) }
                ?: error.message
                    ?.let { showMessage(it) }
        }

        override fun showMessage(messageRes: Int) {
            showMessage(context.resources.getString(messageRes))
        }

        private var toast: Toast? = null
        override fun showMessage(message: CharSequence) {
            toast?.cancel()
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            toast?.show()
        }

    }
}