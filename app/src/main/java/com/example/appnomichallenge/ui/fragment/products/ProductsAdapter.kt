package com.example.appnomichallenge.ui.fragment.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.databinding.RowProductsBinding
import com.example.appnomichallenge.ui.RecyclerItemClickListener
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.ui.ext.setVisibility
import com.example.appnomichallenge.util.DateUtils

class ProductsAdapter(
    private val productList: MutableList<Product>,
    private val onClicked: RecyclerItemClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: RowProductsBinding,
        private val onClicked: RecyclerItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                viewGroup: ViewGroup,
                onClicked: RecyclerItemClickListener,
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = RowProductsBinding.inflate(layoutInflater, viewGroup, false)
                return ViewHolder(binding = binding, onClicked = onClicked)
            }
        }

        init {
            itemView.setOnClickListener { onClicked(adapterPosition) }
        }

        fun bind(item: Product) {
            binding.apply {
               tvTitle.text = item.title
               tvPriceValue.text = item.price.toString()
               tvPriceCurrency.text = item.currency
               tvCreateDate.text = DateUtils.getDateFormat(item.createDate!!)
               ivImage.load(item.featuredImage?.thumbnail)
                item.campaignPrice?.let {
                    tvCampaignPriceValue.text = it.toString()
                    tvCampaignPriceCurrency.text = item.currency
                } ?: run {
                    tvCampaignPriceValue.setVisibility(isVisible = false)
                    tvCampaignPriceCurrency.setVisibility(isVisible = false)
                    tvCampaignPrice.setVisibility(isVisible = false)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(viewGroup = parent, onClicked = onClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(item = productList[position])

    override fun getItemCount(): Int = productList.size
}
