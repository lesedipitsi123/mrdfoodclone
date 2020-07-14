package apps.studios.bt.mrdfoodclone.models

import androidx.annotation.DrawableRes

data class Filter(var filterLabel: String, @DrawableRes var filterIllustration: Int, val colorRes: Int)