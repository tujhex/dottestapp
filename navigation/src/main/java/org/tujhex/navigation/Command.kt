package org.tujhex.navigation

/**
 * @author tujhex
 * since 21.01.20
 */
sealed class Command(val screen: Screen) {
    class Forward(screen: Screen) : Command(screen)
}