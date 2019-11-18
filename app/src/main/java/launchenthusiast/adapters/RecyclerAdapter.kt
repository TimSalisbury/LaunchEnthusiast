package launchenthusiast.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import launchenthusiast.R
import launchenthusiast.adapters.viewholders.BaseViewHolder
import launchenthusiast.adapters.viewholders.EngineExtendedViewHolder
import launchenthusiast.adapters.viewholders.EngineViewHolder
import launchenthusiast.adapters.viewholders.RocketViewHolder
import launchenthusiast.domain.models.DatabaseObject

class RecyclerAdapter<T>(private val dataset : List<T>,
                         private val databaseObjectType: DatabaseObject) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    private enum class ViewType(val id : Int){
        BASE(1),
        EXTENDED(2)
    }

    private val viewTypes : MutableList<ViewType>

    init {
        viewTypes = MutableList(dataset.size){ViewType.BASE}
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypes[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        println(viewType)
        return when(databaseObjectType){
            DatabaseObject.ENGINE -> when(viewType){
                1->{
                    val view =  LayoutInflater.from(parent.context).inflate(R.layout.engine_view_holder, parent, false)//DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.engine_extended_view_holder, parent, false)
                    return EngineViewHolder(view) as BaseViewHolder<T>
                }
                else->{
                    val view =  LayoutInflater.from(parent.context).inflate(R.layout.engine_extended_view_holder, parent, false)//DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), R.layout.engine_extended_view_holder, parent, false)
                    EngineExtendedViewHolder(view) as BaseViewHolder<T>
                }
            }
            DatabaseObject.ROCKET->{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.rocket_view_holder, parent, false)
                RocketViewHolder(view) as BaseViewHolder<T>
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(dataset[position], {onClick(position)})
    }

    private fun onClick(position: Int){
        if(viewTypes[position] == ViewType.EXTENDED){
            viewTypes[position] = ViewType.BASE
        }else{
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                viewTypes.replaceAll { ViewType.BASE }
//            }else{
//                for(i in 0..viewTypes.size){
//                    viewTypes[i] = ViewType.BASE
//                }
//            }
            viewTypes[position] = ViewType.EXTENDED
        }
        notifyDataSetChanged()
    }
}