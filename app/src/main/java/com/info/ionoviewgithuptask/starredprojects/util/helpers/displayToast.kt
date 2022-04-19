package com.info.ionoviewgithuptask.starredprojects.util.helpers

import android.content.Context
import android.widget.Toast

fun displayToast(textResourceId:Int,context: Context) {
    Toast.makeText(context,textResourceId,Toast.LENGTH_LONG).show()

}
fun displayToast(text:String,context: Context) {
    Toast.makeText(context,text,Toast.LENGTH_LONG).show()
}

