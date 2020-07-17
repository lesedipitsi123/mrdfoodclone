package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.interfaces.RestaurantClickListener
import apps.studios.bt.mrdfoodclone.models.Restaurant
import apps.studios.bt.mrdfoodclone.models.ViewType
import kotlinx.android.synthetic.main.activity_restaurant.view.*
import kotlinx.android.synthetic.main.activity_restaurant.view.cover_img
import kotlinx.android.synthetic.main.activity_restaurant.view.restaurant_name
import kotlinx.android.synthetic.main.item_restaurant_stack.view.*


class AdapterRestaurant(val context: Context, val restaurants: List<Restaurant>, val restaurantClickListener: RestaurantClickListener) :
    RecyclerView.Adapter<AdapterRestaurant.MyViewHolder>() {

    init {
        hasStableIds()
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantName = itemView.restaurant_name
        val cover = itemView.cover_img
        val mRoot = itemView.mRoot

        fun bind(item: Restaurant, listener: RestaurantClickListener)
        {
            mRoot.setOnClickListener {
                listener.onRestaurantClick(adapterPosition, item, cover, restaurantName)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return restaurants[position].viewType.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view1 = LayoutInflater.from(context).inflate(R.layout.item_resturants, parent, false)
        val view2 =
            LayoutInflater.from(context).inflate(R.layout.item_restaurant_stack, parent, false)

        return when (viewType) {
            ViewType.NORMAL.type -> {
                MyViewHolder(view1)
            }
            ViewType.STACK.type -> {
                MyViewHolder(view2)
            }
            else -> MyViewHolder(view1)
        }
    }

    override fun getItemCount() = restaurants.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = restaurants[position]
        holder.restaurantName.text = item.restaurant_name
        holder.cover.setImageResource(item.cover_img)
        ViewCompat.setTransitionName(holder.cover, item.restaurant_name + "_imageView");
        ViewCompat.setTransitionName(holder.restaurantName, item.restaurant_name+ "_textView");
        holder.bind(item, restaurantClickListener)
    }
}