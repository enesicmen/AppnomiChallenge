package com.example.appnomichallenge.ui.fragment.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appnomichallenge.data.model.Category
import com.example.appnomichallenge.databinding.RowCategoriesBinding
import com.example.appnomichallenge.ui.RecyclerItemClickListener
import com.example.appnomichallenge.ui.ext.load
import com.example.appnomichallenge.util.DateUtils

class CategoriesAdapter(
    private val categoryList: MutableList<Category>,
    private val onClicked: RecyclerItemClickListener
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: RowCategoriesBinding,
        private val onClicked: RecyclerItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                viewGroup: ViewGroup,
                onClicked: RecyclerItemClickListener,
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = RowCategoriesBinding.inflate(layoutInflater, viewGroup, false)
                return ViewHolder(binding = binding, onClicked = onClicked)
            }
        }

        init {
            itemView.setOnClickListener { onClicked(adapterPosition) }
        }

        fun bind(item: Category) {
            binding.apply {
                tvName.text = item.name
                ivCategory.load(item.icon)
                tvCreateDate.text = DateUtils.getDateFormat(item.createDate!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(viewGroup = parent, onClicked = onClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(item = categoryList[position])

    override fun getItemCount(): Int = categoryList.size
}
