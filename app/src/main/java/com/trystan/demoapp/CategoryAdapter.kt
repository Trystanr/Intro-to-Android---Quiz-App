package com.trystan.demoapp

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item.view.*

class CategoryAdapter(
    private val categoryList: List<CategoryItem>,
    var mClickListener: OnCategoryItemClickListener
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = categoryList.size

    // Most valuable in terms of effeciency, do not do too many calculations here
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val currentItem = categoryList[position]
//        holder.textView.text = currentItem.text
        holder.initializer(categoryList.get(position), mClickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView : TextView = itemView.category_text
        private val imageView: ImageView = itemView.category_image

        fun initializer(categoryItem: CategoryItem, action: OnCategoryItemClickListener) {
            textView.text = categoryItem.text

            //Glide.with(this).load(Uri.parse("file:///android_asset/${newQuestion.image}")).into(binding.imageView2)
            Glide.with(itemView).load(Uri.parse("file:///android_asset/${categoryItem.icon}")).into(imageView)


            textView.setOnClickListener { view ->
                action.onCategoryClick(categoryItem, adapterPosition, view)
            }
        }
    }
}

interface OnCategoryItemClickListener {
    fun onCategoryClick(category: CategoryItem, position: Int, view: View)

}