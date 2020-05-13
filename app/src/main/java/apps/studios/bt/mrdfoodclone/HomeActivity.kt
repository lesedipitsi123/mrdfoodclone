package apps.studios.bt.mrdfoodclone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.models.Restaurant
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_resturants.view.*

class HomeActivity : AppCompatActivity() {

    var addressString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val resultIntent : Intent? = getIntent()
        addressString = resultIntent?.getStringExtra("Address")
        initComponents()
    }

    private fun initComponents()
    {
        listRestaurants.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)
            hasFixedSize()
            isNestedScrollingEnabled = true
            adapter = RestaurantsAdapter(this@HomeActivity, demoRestaurants())
        }

        address.text = addressString

        my_location.setOnClickListener {
            startActivity(Intent(this@HomeActivity, MapActivity::class.java))
            finish()
        }
    }

    private fun demoRestaurants()= mutableListOf(Restaurant(getString(R.string.sample_restaurant1),
        getString(R.string.sample_restaurant_tag1), R.mipmap.demo_img),
        Restaurant(getString(R.string.sample_restaurant2),
            getString(R.string.sample_restaurant_tag2), R.drawable.clothestwo),
       Restaurant(getString(R.string.sample_restaurant3),
            getString(R.string.sample_restaurant_tag3), R.drawable.gameui)
        )


    private inner class RestaurantsAdapter(val context: Context, val listOfRestaurants: MutableList<Restaurant>) : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>()
    {
        init {
            hasStableIds()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
            val view =LayoutInflater.from(context).inflate(R.layout.item_resturants, parent, false)

            return RestaurantViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listOfRestaurants.size
        }

        override fun getItemId(position: Int): Long {
            return listOfRestaurants[position].hashCode().toLong()
        }
        override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
            val item = listOfRestaurants[position]
            holder.restaurant_name.text = item.restaurant_name
            holder.restaurant_tags.text = item.restaurant_tags
            holder.cover_img.setImageResource(item.cover_img)
        }

        inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        {
            val cover_img = itemView.cover_img
            val restaurant_name = itemView.restaurant_name
            val restaurant_tags = itemView.restaurant_tags
            val ratings = itemView.restaurant_rating
        }

    }
}
