package apps.studios.bt.mrdfoodclone.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.SupportMapFragment

class DraggableMap : SupportMapFragment() {

    private lateinit var mapWrapper: MapWrapper

    override fun onCreateView(inflater: LayoutInflater, bundle: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mapView =  super.onCreateView(inflater, bundle, savedInstanceState)
        mapWrapper = MapWrapper(activity!!)
        mapWrapper.addView(mapView)

        return mapWrapper
    }

    fun setOnMapDragListener(listener: MapWrapper.OnDragListener)
    {
        mapWrapper.mapDragListener = listener
    }
}