package com.info.ionoviewgithuptask.starredprojects.domain.adapterusecase

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

typealias LoadPhoto = LoadPhotoUseCase

class LoadPhotoUseCase {
    operator fun invoke(imageView: ImageView, url: String, placeholderResourceId: Int,context: Context) =
        Glide.with(imageView).load(url)
            .placeholder(
                ContextCompat.getDrawable(
                    context,
                    placeholderResourceId
                )
            ).into(imageView)
}