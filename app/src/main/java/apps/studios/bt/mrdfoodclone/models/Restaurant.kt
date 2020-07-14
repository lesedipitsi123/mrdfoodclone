package apps.studios.bt.mrdfoodclone.models

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

data class Restaurant(val restaurant_name: String ="", val restaurant_tags:String ="",
                      @DrawableRes val cover_img: Int = 0)