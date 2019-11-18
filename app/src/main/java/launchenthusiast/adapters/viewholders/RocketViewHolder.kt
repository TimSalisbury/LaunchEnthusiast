package launchenthusiast.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import launchenthusiast.R
import launchenthusiast.domain.models.DatabaseObject
import launchenthusiast.domain.models.Rocket
import launchenthusiast.views.ViewPopulator

/**
 * Base ViewHolder for the rocket, seen before the viewer extends the view
 */
class RocketViewHolder(view : View) : BaseViewHolder<Rocket>(view, DatabaseObject.ROCKET) {

    override fun bind(bindingObject: Rocket, listener : ()->Unit) {
        ViewPopulator.populateView(view, bindingObject)
        view.setOnClickListener { listener.invoke() }
    }
}