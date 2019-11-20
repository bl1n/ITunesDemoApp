package team.lf.itunesdemoapp.utils

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import team.lf.itunesdemoapp.R

@BindingAdapter("HQImageCover")
fun bindHQImageCover(imgView: ImageView, imgUrl: String?) {
    val image = imgUrl?.replace("100x100bb", "313x0w") // to get a good image resource
    image?.let {
        val imgUri = image.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}


