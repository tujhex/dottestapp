package org.tujhex.navigation

/**
 * @author tujhex
 * since 21.01.20
 */
sealed class Command {
    class Forward(val screen: Screen) : Command()
    object Root : Command()
}