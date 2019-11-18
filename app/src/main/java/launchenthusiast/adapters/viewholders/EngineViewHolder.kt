package launchenthusiast.adapters.viewholders

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
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import launchenthusiast.R
import launchenthusiast.cache.ImageCache
import launchenthusiast.domain.models.DatabaseObject
import launchenthusiast.domain.models.Engine
import launchenthusiast.views.ViewPopulator
import kotlin.reflect.KFunction1

/**
 * Base ViewHolder for the Engine, just seen when the view is not extended
 */
class EngineViewHolder(view: View) :
    BaseViewHolder<Engine>(view, DatabaseObject.ENGINE){

    override fun bind(bindingObject: Engine, listener : ()->Unit) {
        ViewPopulator.populateView(view, bindingObject)
        view.setOnClickListener { listener.invoke() }
    }
}