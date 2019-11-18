package launchenthusiast.adapters.viewholders

import android.view.View
import android.widget.TextView
import launchenthusiast.R
import launchenthusiast.domain.models.DatabaseObject
import launchenthusiast.domain.models.Engine
import launchenthusiast.views.ViewPopulator

/**
 * View holder for the extended engine view, Seen when the user presses a engine in the RecyclerView
 */
class EngineExtendedViewHolder(view : View) : BaseViewHolder<Engine>(view, DatabaseObject.ENGINE){

    override fun bind(bindingObject: Engine, listener: () -> Unit) {
        ViewPopulator.populateView(view, bindingObject)
        ViewPopulator.populateView(view, bindingObject.thrust)
        ViewPopulator.populateView(view, bindingObject.dimension)
        view.setOnClickListener { listener.invoke() }
    }
}