package org.tujhex.dottestapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.tujhex.dottestapp.DotAppComponent
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.HasDiComponent
import org.tujhex.dottestapp.login.model.LoginProviderFactory
import org.tujhex.dottestapp.login.model.LoginViewModel
import org.tujhex.dottestapp.main.model.MainProviderFactory
import org.tujhex.dottestapp.main.model.MainViewModel
import javax.inject.Inject

/**
 * @author tujhex
 * since 21.01.20
 */

class MainActivity : AppCompatActivity(), HasDiComponent<MainComponent> {
    override fun getComponent(): MainComponent = mainComponent

    @Inject

    lateinit var factory: MainProviderFactory

    @Inject
    lateinit var loginFactory: LoginProviderFactory

    private lateinit var mainComponent: MainComponent
    lateinit var toggle: ActionBarDrawerToggle

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainComponent = (application as HasDiComponent<DotAppComponent>)
            .getComponent()
            .plus(
                NavigationModule(
                    this.supportFragmentManager,
                    R.id.container
                )
            )
        mainComponent.inject(this)
        ViewModelProvider(this, factory)[MainViewModel::class.java].goToLogin()
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        drawer.addDrawerListener(toggle)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val isHandled =
            ViewModelProvider(this, loginFactory)[LoginViewModel::class.java].handleActivityResult(
                requestCode,
                resultCode,
                data
            )
        if (!isHandled) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
