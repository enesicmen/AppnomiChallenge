package com.example.appnomichallenge.ui.common.ext

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.appnomichallenge.R
import com.squareup.picasso.Picasso

fun ImageView?.load(
    url: String?,
    withPlaceholder: Boolean = true,
    withError: Boolean = true,
    placeHolder: Drawable? = null
) {
    this?.let { imageView ->
        url?.let {
            val requestCreator = Picasso.get().load(it)
            val placeHolderDrawable: Drawable =
                placeHolder ?: ContextCompat.getDrawable(context, R.drawable.placeholder)!!
            if (withPlaceholder) {
                requestCreator.placeholder(placeHolderDrawable)
            }
            if (withError) {
                requestCreator.error(placeHolderDrawable)
            }
            requestCreator.into(imageView)
        }
    }
}
