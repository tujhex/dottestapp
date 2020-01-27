package org.tujhex.dottestapp.core.ui.picasso

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * @author tujhex
 * since 27.01.20
 */
interface ProfileImageLoader {
    fun loadImage(url: String, target: ImageView)

    class Impl : ProfileImageLoader {
        private val transformation = CircleTransformation()
        override fun loadImage(url: String, target: ImageView) {
            Picasso
                .get()
                .load(url)
                .transform(transformation)
                .into(target)
        }

    }
}