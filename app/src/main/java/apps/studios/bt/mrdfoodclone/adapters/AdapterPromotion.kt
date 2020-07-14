package apps.studios.bt.mrdfoodclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import apps.studios.bt.mrdfoodclone.R
import apps.studios.bt.mrdfoodclone.models.Promotion
import kotlinx.android.synthetic.main.item_restaurant_offers.view.*

class AdapterPromotion(val context: Context, val promotions: List<Promotion>) :
    RecyclerView.Adapter<AdapterPromotion.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_restaurant_offers, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = promotions.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = promotions[position]
        holder.promotionText.text = item.titlePromotion
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val promotionText = itemView.sample_text

    }
}