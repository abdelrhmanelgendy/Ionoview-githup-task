package com.info.ionoviewgithuptask.starredprojects.util.extensions

import android.view.View
import com.info.ionoviewgithuptask.starredprojects.presentation.StarredProjectsActivity

fun StarredProjectsActivity.showInternetConnectionDialog(isInternetAvailable: Boolean, view: View) {

    view.visibility = if (isInternetAvailable) View.VISIBLE else View.GONE
}