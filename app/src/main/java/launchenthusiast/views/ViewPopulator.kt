package launchenthusiast.views

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.engine_view_holder.view.*
import launchenthusiast.cache.ImageCache
import kotlin.reflect.KProperty
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberProperties
import launchenthusiast.R

class ViewPopulator {
    companion object{
        fun <T : Any> populateView(view : View, obj : T) {
            for(property in obj::class.memberProperties){
                val id = view.resources.getIdentifier(property.name, "id", view.context.packageName)
                if(id != 0){
                    when(val component = view.findViewById<View>(id)){
                        is TextView-> component.text = property.getter.call(obj).toString()
                        is ImageView-> setImage(view, property.getter.call(obj).toString(), component, RequestOptions().transform(
                            CenterCrop(), RoundedCorners(9)
                        ))
                    }
                }
            }
        }

        private fun setImage(view : View, imageURL: String, imageView : ImageView, options : RequestOptions){
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
            if(ImageCache.containsKey(imageURL)){
                imageView.setImageBitmap(ImageCache[imageURL])
                progressBar?.visibility = View.INVISIBLE
            }else{
                Glide
                    .with(view)
                    .load(imageURL)
                    .apply(options)
                    .listener(object: RequestListener<Drawable> {
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.findViewById<ProgressBar>(R.id.progressBar)
                            ImageCache[imageURL] = (resource as BitmapDrawable).bitmap
                            progressBar?.visibility = View.INVISIBLE
                            return false
                        }
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    })
                    .into(imageView)
            }
        }
    }
}