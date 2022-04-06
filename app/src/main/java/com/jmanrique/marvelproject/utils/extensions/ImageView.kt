package com.jmanrique.marvelproject.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String, placeholder: Int?) {
    if (placeholder != null)
        Glide.with(context).load(url).placeholder(placeholder).into(this)
    else
        Glide.with(context).load(url).into(this)
}