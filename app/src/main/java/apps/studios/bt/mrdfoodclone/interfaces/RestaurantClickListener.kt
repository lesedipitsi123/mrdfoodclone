package apps.studios.bt.mrdfoodclone.interfaces

import android.widget.ImageView
import android.widget.TextView
import apps.studios.bt.mrdfoodclone.models.Restaurant

interface RestaurantClickListener {
    fun onRestaurantClick(pos: Int, item: Restaurant, sharedImageView: ImageView, sharedTextView: TextView)
}