package team.lf.itunesdemoapp.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import team.lf.itunesdemoapp.R

@BindingAdapter("highQualityImageCover")
fun ImageView.setHQImage(imgUrl: String?) {
    val image = imgUrl?.replace("100x100bb", "313x0w") // to get a good image resource
    image?.let {
        val imgUri = image.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setCoverImage( imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(
                RequestOptions()
                    .apply(RequestOptions()
                        .error(R.drawable.ic_broken_image))
            )

            .into(this)
    }
}

//@BindingAdapter("dateFormatted")
//fun bindDate(textView: TextView, item: DomainModel.Collection) {
//    val timestamp = Timestamp.valueOf(item.releaseDate)
//    textView.text = timestamp.toString()
//
////    textView.text =  SimpleDateFormat("YYYY").format(item.releaseDate).toString()
//}

//@SuppressLint("SimpleDateFormat")
//fun convertLongToDateString(systemTime: String): String {
//
//    return SimpleDateFormat("EEEE MMM-dd-yyyy' Time: 'HH:mm")
//        .format(systemTime).toString()
//}




