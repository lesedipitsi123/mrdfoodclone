package apps.studios.bt.mrdfoodclone.models

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes

enum class ViewType(val type: Int) {
    NORMAL(0), STACK(1)
}
data class Restaurant(
    val restaurant_name: String = "", val restaurant_tags: String = "",
    @DrawableRes val cover_img: Int = 0, val viewType: ViewType = ViewType.NORMAL
)