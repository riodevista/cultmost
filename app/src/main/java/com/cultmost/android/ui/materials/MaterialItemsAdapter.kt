package com.cultmost.android.ui.Items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.cultmost.android.R
import com.cultmost.android.domain.Item
import kotlinx.android.synthetic.main.holder_main_screen_course.view.title
import kotlinx.android.synthetic.main.holder_material_item.view.*

/**
 * Created by Dmitry Bochkov on 30.07.2020.
 */

class MaterialItemsAdapter(
    private val glide: RequestManager,
    private var arrayOfItems: Array<Item>
) :
    RecyclerView.Adapter<MaterialItemsAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_material_item, parent, false) as View
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayOfItems[position]
        holder.view.title.text = item.title
        holder.view.duration.text = "${item.duration} • ${item.date}"
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(
                position,
                arrayOfItems[position]
            )
        }
    }

    override fun getItemCount() = arrayOfItems.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int, item: Item)
    }

    fun setData(newData: Array<Item>) {
        arrayOfItems = newData
        notifyDataSetChanged()
    }
}