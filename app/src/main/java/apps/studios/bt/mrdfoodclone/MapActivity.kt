package apps.studios.bt.mrdfoodclone

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.AsyncTask
import android.os.Bundle
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import apps.studios.bt.mrdfoodclone.views.DraggableMap
import apps.studios.bt.mrdfoodclone.views.MapWrapper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_map.*
import java.io.IOException
import java.util.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback, MapWrapper.OnDragListener {

    private lateinit var locationTextView: TextView
    private lateinit var googleMapFragment: DraggableMap
    private var lastLocation: Location? = null
    private lateinit var fusedLocation: FusedLocationProviderClient
    private var googleMap: GoogleMap? = null
    private var coord_marker_x: Int = 0
    private var coord_marker_y: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initComponents()
    }

    private fun initComponents() {
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        locationTextView = sheet_location
        coord_marker_x = Math.round(map_pointer.x)
        coord_marker_y =
            Math.round(map_pointer.y).plus(map_pointer.width / 2).plus(map_pointer.height)

        googleMapFragment = supportFragmentManager.findFragmentById(R.id.map) as DraggableMap
        googleMapFragment.getMapAsync(this)
        googleMapFragment.setOnMapDragListener(this)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocation.lastLocation.addOnSuccessListener {
            lastLocation = it
            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(lastLocation?.latitude!!, lastLocation?.longitude!!),
                    17f
                )

            )
        }
    }

    fun saveLocation(v: View) {
        val intent = Intent(this, HomeActivity::class.java)
        if (locationTextView.text.trim().isNotEmpty()) {
            intent.putExtra("Address", locationTextView.text.trim().toString())
        }
        startActivity(intent)
        finish()

    }

    private fun updateLocation(coords: LatLng) {
        GetGoogleMapAddress(this).execute(coords)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map

        googleMap?.uiSettings!!.isMyLocationButtonEnabled = false
        googleMap?.uiSettings!!.isScrollGesturesEnabledDuringRotateOrZoom = false
        googleMap?.uiSettings!!.isRotateGesturesEnabled = false
        googleMap?.setMaxZoomPreference(18f)
        if (lastLocation == null) {
            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(-26.195246, 28.034088),
                    16f
                )

            )
        }


    }

    override fun onMapDrag(motionEvent: MotionEvent?) {
        locationTextView.text = getString(R.string.loading)
        // map_pointer?.isEnabled = true
        map_pointer?.animate()!!.translationY(-24f).setDuration(200).start()
    }

    override fun onMapRelease(action_release: Int?) {
        map_pointer?.animate()!!.translationY(0f)
            .setInterpolator(BounceInterpolator())
            .setDuration(420).start()

        coord_marker_x = Math.round(map_pointer.x)
        coord_marker_y =
            Math.round(map_pointer.y).plus(map_pointer.width / 2).plus(map_pointer.height)

        updateLocation(
            googleMap?.projection!!.fromScreenLocation(
                Point(
                    coord_marker_x,
                    coord_marker_y
                )
            )
        )
    }

    override fun onResume() {
        super.onResume()
        googleMap?.clear()
    }

    companion object {
        class GetGoogleMapAddress(private val activity: MapActivity) :
            AsyncTask<LatLng, Void, String>() {

            init {
                if (Looper.myLooper() == null) Looper.prepare()
            }


            override fun doInBackground(vararg latLng: LatLng): String? {

                try {
                    val completerAddress = StringBuilder()

                    val geocoder = Geocoder(activity, Locale.getDefault())

                    val addressList =
                        geocoder.getFromLocation(latLng[0].latitude, latLng[0].longitude, 1)

                    if (addressList?.size!! > 0) {
                        val obj: Address = addressList[0]

                        completerAddress.append(
                            obj.getAddressLine(0).split(',')[0]
                        )
                            .append(", ").append(obj.countryName)
                    }
                    return completerAddress.toString()

                } catch (e: IOException) {
                    return null
                }
            }

            override fun onPostExecute(completeAddress: String?) {

                if (completeAddress == null) {
                    Toast.makeText(activity, R.string.google_maps_error, Toast.LENGTH_LONG).show()
                    //activity.locationTextView.text = activity.getString(R.string.sample_location)
                    return
                }
                activity.locationTextView.text = completeAddress
            }
        }
    }

}
