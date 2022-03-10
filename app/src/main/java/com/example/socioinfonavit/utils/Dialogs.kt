package com.example.socioinfonavit.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.example.socioinfonavit.R
import com.example.socioinfonavit.ui.home.HomeViewModel

fun showLogoutAlert(context: Context, viewModel: HomeViewModel) {

    AlertDialog.Builder(context as Activity)
        .setTitle(context.resources.getString(R.string.logout))
        .setMessage(context.resources.getString(R.string.logout_question))
        .setPositiveButton(context.resources.getString(R.string.confirm_alert)) { dialog, _ ->
            dialog.dismiss()
            viewModel.logout()
        }
        .setNegativeButton(context.resources.getString(R.string.cancel_alert)) { dialog, _ ->
            dialog.dismiss()
        }.show()

}