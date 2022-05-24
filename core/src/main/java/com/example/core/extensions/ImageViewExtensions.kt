package com.example.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.downloadImage(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}