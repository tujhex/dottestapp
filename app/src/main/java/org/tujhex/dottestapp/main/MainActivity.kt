package org.tujhex.dottestapp.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.tujhex.dottestapp.DotAppComponent
import org.tujhex.dottestapp.R
import org.tujhex.dottestapp.core.HasDiComponent
import org.tujhex.dottestapp.core.ui.HasChangeableToolbar
import org.tujhex.dottestapp.core.ui.HasDrawer
import org.tujhex.dottestapp.login.model.LoginProviderFactory
import org.tujhex.dottestapp.login.model.LoginViewModel
import org.tujhex.dottestapp.main.model.MainProviderFactory
import org.tujhex.dottestapp.main.model.MainViewModel
import javax.inject.Inject

/**
 * @author tujhex
 * since 21.01.20
 */

class MainActivity : AppCompatActivity(), HasDiComponent<MainComponent>, HasDrawer,
                     HasChangeableToolbar {

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
        toggle.syncState()
        drawer.addDrawerListener(toggle)
    }

    override fun onBackPressed() {
        if (drawer.isDrawerVisible(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return
        }
        if (supportFragmentManager.fragments.size > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    private fun toggleDrawer() {
        if (drawer.isDrawerVisible(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            drawer.openDrawer(GravityCompat.START)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (isDrawerNotLocked()) {
                toggleDrawer()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isDrawerNotLocked() =
        drawer.getDrawerLockMode(GravityCompat.START) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED

    override fun lockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun setupAppBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(isDrawerNotLocked())
            setDisplayShowHomeEnabled(isDrawerNotLocked())
        }
        toggle.syncState()
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
