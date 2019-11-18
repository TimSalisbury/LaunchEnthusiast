package launchenthusiast.adapters.viewholders

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import launchenthusiast.R
import launchenthusiast.cache.ImageCache
import launchenthusiast.domain.models.DatabaseObject

/**
 * Base view holder for holding containing information about different objects in the database. The data type this view holder holds
 * is described by T
 */
abstract class BaseViewHolder<T>(internal val view : View, private val databaseObjectType : DatabaseObject) : RecyclerView.ViewHolder(view){

    /**
     * Binds an object to this view holder by populating the fields with data from binding object
     * @param bindingObject     The object to bind
     * @param listener          A listener to call when the view holder is pressed
     */
    abstract fun bind(bindingObject: T, listener : ()->Unit)
}