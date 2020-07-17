package apps.studios.bt.mrdfoodclone.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import kotlinx.android.parcel.Parcelize


@Parcelize
enum class ViewType(val type: Int) : Parcelable {
    NORMAL(0), STACK(1)
}
@Parcelize
data class Restaurant(
    val restaurant_name: String = "", val restaurant_tags: String = "",
    @DrawableRes val cover_img: Int = 0, val viewType: ViewType = ViewType.NORMAL
) : Parcelable