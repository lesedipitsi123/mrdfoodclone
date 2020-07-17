package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import kotlinx.android.synthetic.main.item_restaurant_menu.view.*

class AdapterMenu(val context: Context, val menuList: MutableList<String>) :
    RecyclerView.Adapter<AdapterMenu.MyViewHolder>() {

    init {
        hasStableIds()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuName = itemView.txtMenu
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_restaurant_menu, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return menuList.get(position).hashCode().toLong()
    }

    override fun getItemCount() = menuList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.menuName.text = menuList[position]
    }
}