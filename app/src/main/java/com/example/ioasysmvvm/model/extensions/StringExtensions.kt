package com.example.ioasysmvvm.model.extensions

import android.util.Patterns

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}