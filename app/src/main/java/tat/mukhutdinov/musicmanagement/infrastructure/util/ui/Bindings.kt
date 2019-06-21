package tat.mukhutdinov.musicmanagement.infrastructure.util.ui

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import tat.mukhutdinov.musicmanagement.R

@BindingAdapter("image", "placeholder")
fun setImage(image: ImageView, url: String, placeholder: Drawable) {
    if (url.isEmpty()) {
        image.setImageDrawable(placeholder)
    } else {
        Picasso.get()
            .load(url)
            .placeholder(placeholder)
            .into(image)
    }
}

@BindingAdapter("backgroundColor")
fun setBackgroundColor(view: View, @ColorInt color: Int) {
    val current = ContextCompat.getColor(view.context, R.color.colorPrimaryDark)

    val animator = ObjectAnimator.ofInt(view, "backgroundColor", current, color)
    animator.duration = 250
    animator.setEvaluator(ArgbEvaluator())
    animator.start()
}