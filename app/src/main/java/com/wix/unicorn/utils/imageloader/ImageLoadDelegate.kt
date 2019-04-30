package com.wix.unicorn.utils.imageloader

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide


interface ImageLoadDelegate {
    fun loadImage(view: ImageView, url: String?)

    fun loadImage(view: ImageView, uri: Uri?)
}

class GlideLoadDelegate : ImageLoadDelegate {

    override fun loadImage(view: ImageView, url: String?) {
        Glide.with(view).clear(view)
        Glide.with(view)
            .load(url)
            .into(view)
    }

    override fun loadImage(view: ImageView, uri: Uri?) {
        Glide.with(view).clear(view)
        Glide.with(view)
            .load(uri)
            .into(view)
    }
}