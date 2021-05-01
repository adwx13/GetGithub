package pri.sungjin.getgithub.viewmodel

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent
import com.bumptech.glide.Glide
import pri.sungjin.getgithub.R


class BaseBindingComponent: DataBindingComponent {

    @BindingAdapter("glide_img_url")
    fun setGlideImage(view: ImageView, url: String?) {
        Glide.with(view).load(url).placeholder(R.drawable.image_not_supported).into(view)
    }

    override fun getBaseBindingComponent(): BaseBindingComponent {
        return this
    }
}