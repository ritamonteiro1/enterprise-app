package com.example.ioasysmvvm.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.downloadImage(imageUrl: String) {
    Glide.with(context).load(imageUrl).into(this)
}