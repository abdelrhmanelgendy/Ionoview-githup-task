package com.info.ionoviewgithuptask.starredprojects.util.extensions

import android.content.Context
import android.view.View
import com.info.ionoviewgithuptask.starredprojects.presentation.ui.StarredProjectsActivity

fun Context.showInternetConnectionDialog(isInternetAvailable: Boolean, view: View) {
    view.visibility = if (isInternetAvailable) View.VISIBLE else View.GONE
}