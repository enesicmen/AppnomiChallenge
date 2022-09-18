package com.example.appnomichallenge.ui.productlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.appnomichallenge.R
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.data.model.Product
import com.example.appnomichallenge.databinding.RowProductsBinding
import com.example.appnomichallenge.ui.common.RecyclerItemClickListener
import com.example.appnomichallenge.ui.common.ext.load
import com.example.appnomichallenge.ui.common.ext.setVisibility
import com.example.appnomichallenge.util.DateUtils


class ProductsAdapter(
    private val productList: MutableList<Product>,
    private val onClicked: RecyclerItemClickListener
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    class ViewHolder(
        private val binding: RowProductsBinding,
        private val onClicked: RecyclerItemClickListener,
        private val mContext: Context,
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                viewGroup: ViewGroup,
                onClicked: RecyclerItemClickListener,
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val context = viewGroup.context
                val binding = RowProductsBinding.inflate(layoutInflater, viewGroup, false)
                return ViewHolder(binding = binding, onClicked = onClicked,context)
            }
        }

        init {
            itemView.setOnClickListener { onClicked(adapterPosition) }
        }

        fun bind(item: Product) {
            binding.apply {
                tvTitle.text = item.title
                tvPrice.text = mContext.resources.getString(R.string.price, item.price, item.currency)
                tvCreateDate.text = DateUtils.getDateFormat(item.createDate!!)
               ivImage.load(item.featuredImage?.thumbnail)
                item.campaignPrice?.let {
                    tvCampaignPrice.text = mContext.resources.getString(R.string.campaign_price, it, item.currency)
                } ?: run {
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
