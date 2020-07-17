package apps.studios.bt.mrdfoodclone

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.adapters.AdapterMenu
import apps.studios.bt.mrdfoodclone.adapters.AdapterMenuHighlights
import apps.studios.bt.mrdfoodclone.models.MenuHighlight
import apps.studios.bt.mrdfoodclone.models.Restaurant
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_restaurant.*
import java.lang.Exception

class RestaurantActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        supportPostponeEnterTransition()

        toolbar.setNavigationIcon(R.mipmap.baseline_arrow_back_white_24)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        val extras = intent.extras
        val restaurantItem =
            extras!!.getParcelable<Restaurant>(HomeActivity.EXTRA_RESTAURANT_ITEM) as Restaurant

        val imageSrc: Int = restaurantItem.cover_img

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val imageTransitionName =
                extras.getString(HomeActivity.EXTRA_RESTAURANT_COVER_TRANSITION_NAME)
            val titleTransitionName =
                extras.getString(HomeActivity.EXTRA_RESTAURANT_NAME_TRANSITION_NAME)
            restaurant_name.transitionName = titleTransitionName
            cover_img.transitionName = imageTransitionName
        }
        initColorPalette(imageSrc)
        restaurant_name.text = restaurantItem.restaurant_name

        Picasso.get()
            .load(imageSrc)
            .noFade()
            .into(cover_img, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }
            })

        listMenus.apply {
            layoutManager = LinearLayoutManager(this@RestaurantActivity)
            adapter = AdapterMenu(this@RestaurantActivity, demoMenus())
            hasFixedSize()
        }

        listMenuHighlights.apply {
            layoutManager =
                LinearLayoutManager(this@RestaurantActivity, RecyclerView.HORIZONTAL, false)
            adapter = AdapterMenuHighlights(this@RestaurantActivity, demoMenuHighlights())
            hasFixedSize()
        }
    }

    private fun demoMenus() = mutableListOf(
        "hungry",
        "flamed grill",
        "sides",
        "most popular",
        "thirsty",
        "salads",
        "bowls",
        "fresh on the deck",
        "hungry-ish",
        "chicken promo baby"
    )

    private fun demoMenuHighlights() = mutableListOf(
        MenuHighlight("2 piece special", "R419,99", R.drawable.demo_ad_img),
        MenuHighlight("Number 9", "R99,90", R.drawable.demo_food_img),
        MenuHighlight("Large sandwich", "R100,00", R.drawable.demo_food_img3),
        MenuHighlight("Quarter pounder", "R64,10", R.drawable.demo_food_img2)
    )

    private fun initColorPalette(resId: Int) {
        val image = BitmapFactory.decodeResource(resources, resId)
        val palette = Palette.Builder(image).generate()
        val bgColor = palette.getMutedColor(
            ContextCompat.getColor(this, R.color.colorPrimary)
        )
        detailsHolder.setBackgroundColor(bgColor)
    }

}