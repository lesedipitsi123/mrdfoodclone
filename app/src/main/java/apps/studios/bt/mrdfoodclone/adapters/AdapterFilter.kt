package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.models.Filter
import kotlinx.android.synthetic.main.item_filter.view.*

class AdapterFilter(val context: Context, val filters: List<Filter>) :
    RecyclerView.Adapter<AdapterFilter.MyViewHolder>() {

    init {
        hasStableIds()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterImage = itemView.btnFilter
        val filterName = itemView.txtFilter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_filter, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemId(position: Int): Long {
        return filters[position].hashCode().toLong()
    }

    override fun getItemCount() = filters.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = filters[position]
        holder.filterImage.icon = context.getDrawable(item.filterIllustration)
        holder.filterImage.backgroundTintList = ColorStateList.valueOf(item.colorRes)
        holder.filterName.text = item.filterLabel
    }
}