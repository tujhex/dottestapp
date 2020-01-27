package org.tujhex.dottestapp

import android.content.Context
import dagger.Module
import dagger.Provides
import org.tujhex.dottestapp.core.MessageUtils
import org.tujhex.dottestapp.core.ui.picasso.ProfileImageLoader
import javax.inject.Singleton


/**
 * @author tujhex
 * since 21.01.20
 */
@Module
class DotAppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideMessageUtils(): MessageUtils {
        return MessageUtils.Impl(context)
    }

    @Provides
    @Singleton
    fun provideProfileImageLoader(): ProfileImageLoader {
        return ProfileImageLoader.Impl()
    }

}