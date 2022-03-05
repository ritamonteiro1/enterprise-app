package com.example.ioasysmvvm.extensions

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.ioasysmvvm.R

fun Context.createLoadingDialog(): Dialog {
    val builder = AlertDialog.Builder(this)
    builder.setView(R.layout.progress_dialog)
    val dialog: Dialog = builder.create()
    dialog.setCancelable(false)
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    return dialog
}

fun Context.showErrorDialog(message: String) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message)
        .setCancelable(false)
        .setPositiveButton(this.getString(R.string.alert_dialog_text), null)
    val alert = builder.create()
    alert.show()
}