package org.tujhex.dottestapp.core.ui.picasso

import android.graphics.*
import com.squareup.picasso.Transformation
import kotlin.math.min


/**
 *
 * Copy of  CircleTransformation:
 * [https://github.com/wasabeef/picasso-transformations/blob/master/transformations/src/main/java/jp/wasabeef/picasso/transformations/CropCircleTransformation.java]
 *
 * @author tujhex
 * since 27.01.20
 */
class CircleTransformation : Transformation {
    override fun key(): String {
        return "circle_transformation"
    }

    override fun transform(source: Bitmap): Bitmap {
        val size = min(source.width, source.height)

        val width = (source.width - size) / 2f
        val height = (source.height - size) / 2f

        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        if (width != 0f || height != 0f) {
            // source isn't square, move viewport to center
            val matrix = Matrix()
            matrix.setTranslate(-width, -height)
            shader.setLocalMatrix(matrix)
        }
        paint.shader = shader
        paint.isAntiAlias = true

        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)

        source.recycle()

        return bitmap
    }
}