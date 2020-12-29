package com.cultmost.android.ui.materials

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.cultmost.android.R
import com.cultmost.android.domain.Material
import kotlinx.android.synthetic.main.holder_courses_item.view.*
import kotlinx.android.synthetic.main.holder_main_screen_course.view.image
import kotlinx.android.synthetic.main.holder_main_screen_course.view.title

/**
 * Created by Dmitry Bochkov on 30.07.2020.
 */

class MaterialsAdapter(
    private val glide: RequestManager,
    private var arrayOfMaterials: Array<Material>
) :
    RecyclerView.Adapter<MaterialsAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_courses_item, parent, false) as View
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayOfMaterials[position]
        holder.view.title.text = item.title
        holder.view.count.text = item.count
//        holder.view.date.text = item.dae
        glide.load(item.image).centerCrop().into(holder.view.image)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(
                position,
                arrayOfMaterials[position]
            )
        }
    }

    override fun getItemCount() = arrayOfMaterials.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int, material: Material)
    }

    fun setData(newData: Array<Material>) {
        arrayOfMaterials = newData
        notifyDataSetChanged()
    }
}