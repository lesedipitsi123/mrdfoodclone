package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.models.BrowseRestaurant
import kotlinx.android.synthetic.main.item_resturant_browse.view.*

class AdapterBrowseRestaurant(val context: Context, val browseRestaurants: List<BrowseRestaurant>) :
    RecyclerView.Adapter<AdapterBrowseRestaurant.MyViewHolder>() {

    init {
        hasStableIds()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coverImage = itemView.cover_img
        val tags = itemView.restaurant_tags
        val restaurantName = itemView.restaurant_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_resturant_browse, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return browseRestaurants[position].hashCode().toLong()
    }

    override fun getItemCount() = browseRestaurants.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = browseRestaurants[position]
        holder.coverImage.setImageResource(item.coverLogo)
        holder.restaurantName.text = item.restaurant_name
        holder.tags.text = "${item.numberRestaurant} Stores"
    }
}