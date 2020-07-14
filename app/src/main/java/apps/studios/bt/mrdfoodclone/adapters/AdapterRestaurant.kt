package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.models.Restaurant
import kotlinx.android.synthetic.main.item_resturants.view.*

class AdapterRestaurant(val context: Context, val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<AdapterRestaurant.MyViewHolder>() {

    init {
        hasStableIds()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName = itemView.restaurant_name
        val cover = itemView.cover_img
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_resturants, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = restaurants[position]
        holder.restaurantName.text = item.restaurant_name
        holder.cover.setImageResource(item.cover_img)
    }
}