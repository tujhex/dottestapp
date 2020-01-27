package org.tujhex.navigation

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
                is Command.Root -> popBackRoot()
            }
        }

        private fun popBackRoot() {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        private fun forward(command: Command.Forward) {
            val screen = command.screen
            fragmentManager
                .beginTransaction()
                .replace(containerId, screen.target())
                .addToBackStack(screen.key())
                .commit()
        }
    }
}