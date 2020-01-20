package org.tujhex.dottestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.tujhex.dottestapp.navigation.Navigator

/**
 * @author tujhex
 * since 21.01.20
 */

class MainActivity : AppCompatActivity() {
    lateinit var navigator: Navigator
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
