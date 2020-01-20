package org.tujhex.dottestapp.navigation

import androidx.fragment.app.FragmentManager

/**
 * @author tujhex
 * since 21.01.20
 */
interface Navigator {
    fun navigate(command: Command)


    class Impl(
        private val fragmentManager: FragmentManager,
        private val containerId: Int
    ) : Navigator {
        override fun navigate(command: Command) {
            if (fragmentManager.isDestroyed || fragmentManager.isStateSaved) {
                return
            }
            fragmentManager.executePendingTransactions()
            when (command) {
                is Command.Forward -> forward(command)
            }
        }

        private fun forward(command: Command) {
            val screen = command.screen
            fragmentManager
                .beginTransaction()
                .replace(containerId, screen.target())
                .addToBackStack(screen.key())
        }
    }
}