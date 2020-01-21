package org.tujhex.dottestapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
