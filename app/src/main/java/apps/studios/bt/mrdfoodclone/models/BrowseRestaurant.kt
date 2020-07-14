package apps.studios.bt.mrdfoodclone.models

import androidx.annotation.DrawableRes

data class BrowseRestaurant(var restaurant_name: String, val numberRestaurant: Int=0,
                            @DrawableRes var coverLogo: Int=0)