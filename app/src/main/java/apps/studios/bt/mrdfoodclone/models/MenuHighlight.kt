package apps.studios.bt.mrdfoodclone.models

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuHighlight(val name: String, val price: String, @DrawableRes val imageSrc: Int) :
    Parcelable