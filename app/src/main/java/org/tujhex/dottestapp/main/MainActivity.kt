package org.tujhex.dottestapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.tujhex.dottestapp.BaseApplication
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.navigation.LoginScreen
import org.tujhex.navigation.Command
import org.tujhex.navigation.Navigator
import javax.inject.Inject

/**
 * @author tujhex
 * since 21.01.20
 */

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigator: Navigator
    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainComponent = (application as BaseApplication)
            .appComponent
            .plus(
                NavigationModule(
                    this.supportFragmentManager,
                    R.id.container
                )
            )
        mainComponent.inject(this)
        navigator.navigate(
            Command.Forward(
                LoginScreen()
            )
        )
    }
}
