package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.models.Restaurant
import kotlinx.android.synthetic.main.item_choose_restaurant.view.*

class AdapterChooseRestaurant(val context: Context, val chooseRestaurants: List<Restaurant>) :
    RecyclerView.Adapter<AdapterChooseRestaurant.MyViewHolder>() {

    init {
        hasStableIds()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName = itemView.txtRestaurant
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_choose_restaurant, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemId(position: Int): Long {
        return chooseRestaurants[position].hashCode().toLong()
    }

    override fun getItemCount() = chooseRestaurants.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = chooseRestaurants[position]
        holder.restaurantName.text = item.restaurant_name
    }
}