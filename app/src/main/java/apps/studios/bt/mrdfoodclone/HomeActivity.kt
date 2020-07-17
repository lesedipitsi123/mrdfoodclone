package apps.studios.bt.mrdfoodclone

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.adapters.*
import apps.studios.bt.mrdfoodclone.interfaces.RestaurantClickListener
import apps.studios.bt.mrdfoodclone.models.*
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), RestaurantClickListener {

    companion object {
        public const val EXTRA_RESTAURANT_COVER_TRANSITION_NAME = "RestaurantCover"
        public const val EXTRA_RESTAURANT_ITEM = "RestaurantItem"
        public const val EXTRA_RESTAURANT_NAME_TRANSITION_NAME = "RestaurantName"
    }

    private var addressString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val resultIntent: Intent? = intent
        addressString = resultIntent?.getStringExtra("Address")
        initComponents()
    }

    private fun initComponents() {
        address.text = addressString
        listPromotions.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL, false)
            adapter = AdapterPromotion(this@HomeActivity, demoPromotions())
            hasFixedSize()
        }
        listFilters.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL, false)
            adapter = AdapterFilter(this@HomeActivity, demoFilters())
            hasFixedSize()
        }
        listChooseRestaurants.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL, false)
            adapter = AdapterChooseRestaurant(this@HomeActivity, demoChooseRestaurants())
            hasFixedSize()
        }
        listBrowseRestaurants.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.HORIZONTAL, false)
            adapter = AdapterBrowseRestaurant(this@HomeActivity, demoBrowseRestaurants())
            hasFixedSize()
        }
        listRestaurants.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            adapter = AdapterRestaurant(this@HomeActivity, demoRestaurants(), this@HomeActivity)
            hasFixedSize()
        }
        txtNumberRestaurants.text = "${demoRestaurants().size} restaurants near you"
    }

    private fun demoPromotions() = mutableListOf(
        Promotion(getString(R.string.follow_me), R.drawable.demo_food_img3),
        Promotion(getString(R.string.follow_me), R.drawable.demo_food_img3),
        Promotion(getString(R.string.follow_me), R.drawable.demo_food_img3)
    )

    private fun demoFilters() = mutableListOf(
        Filter(getString(R.string.filter1), R.mipmap.sale, R.color.material_red_500),
        Filter(getString(R.string.filter2), R.mipmap.heart, R.color.material_pink_300),
        Filter(getString(R.string.filter3), R.mipmap.bullhorn, R.color.material_green_700)
    )

    private fun demoChooseRestaurants() = mutableListOf(
        Restaurant(getString(R.string.sample_restaurant1)),
        Restaurant(getString(R.string.sample_restaurant2)),
        Restaurant(getString(R.string.sample_restaurant3)),
        Restaurant(getString(R.string.sample_restaurant4))
    )

    private fun demoBrowseRestaurants() = mutableListOf(
        BrowseRestaurant(
            getString(R.string.sample_restaurant_category),
            5,
            R.drawable.demo_food_img2
        ),
        BrowseRestaurant(
            getString(R.string.sample_restaurant_category2),
            6,
            R.drawable.demo_food_img
        ),
        BrowseRestaurant(
            getString(R.string.sample_restaurant_category3),
            4,
            R.drawable.demo_food_img3
        ),
        BrowseRestaurant(getString(R.string.sample_restaurant_category4), 10, R.mipmap.ic_banner)
    )

    private fun demoRestaurants() = mutableListOf(
        Restaurant(getString(R.string.sample_restaurant1), cover_img = R.drawable.demo_food_img),
        Restaurant(
            getString(R.string.sample_restaurant4), cover_img = R.drawable.demo_food_img2,
            viewType = ViewType.STACK
        ),
        Restaurant(getString(R.string.sample_restaurant3), cover_img = R.drawable.demo_food_img3)
    )

    override fun onRestaurantClick(
        pos: Int,
        item: Restaurant,
        sharedImageView: ImageView,
        sharedTextView: TextView
    ) {
        val intent = Intent(this, RestaurantActivity::class.java)
        intent.putExtra(EXTRA_RESTAURANT_ITEM, item)
        intent.putExtra(
            EXTRA_RESTAURANT_COVER_TRANSITION_NAME,
            ViewCompat.getTransitionName(sharedImageView)
        )
        intent.putExtra(
            EXTRA_RESTAURANT_NAME_TRANSITION_NAME,
            ViewCompat.getTransitionName(sharedTextView)
        )

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            Pair<View, String>(sharedImageView, ViewCompat.getTransitionName(sharedImageView)),
            Pair<View, String>(sharedTextView, ViewCompat.getTransitionName(sharedTextView))
        )


        startActivity(intent, options.toBundle())
    }

}
