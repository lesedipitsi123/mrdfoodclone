package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.models.MenuHighlight
import kotlinx.android.synthetic.main.item_menu_highlights.view.*

class AdapterMenuHighlights(val context: Context, val menuList: List<MenuHighlight>) : RecyclerView.Adapter<AdapterMenuHighlights.MyViewHolder>() {

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val menuName = itemView.menuName
        val menuPrice = itemView.menuPrice
        val cover_img = itemView.cover_img

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_menu_highlights, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = menuList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val item = menuList[position]
        holder.menuName.text = item.name
        holder.menuPrice.text = item.price

        holder.cover_img.setImageResource(item.imageSrc)
    }
}