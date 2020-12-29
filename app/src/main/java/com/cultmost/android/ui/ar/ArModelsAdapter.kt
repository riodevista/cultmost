package com.cultmost.android.ui.ArModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.cultmost.android.R
import com.cultmost.android.domain.ArModel
import kotlinx.android.synthetic.main.holder_ar_model.view.*
import kotlinx.android.synthetic.main.holder_main_screen_course.view.description
import kotlinx.android.synthetic.main.holder_main_screen_course.view.title

/**
 * Created by Dmitry Bochkov on 30.07.2020.
 */

class ArModelsAdapter(
    private val glide: RequestManager,
    private var arrayOfArModels: Array<ArModel>
) :
    RecyclerView.Adapter<ArModelsAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_ar_model, parent, false) as View
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arModel = arrayOfArModels[position]
        holder.view.title.text = arModel.title
        holder.view.description.text = "${arModel.description}"
        glide.load(arModel.preview).centerCrop().into(holder.view.preview)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(
                position,
                arrayOfArModels[position]
            )
        }
    }

    override fun getItemCount() = arrayOfArModels.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int, arModel: ArModel)
    }

    fun setData(newData: Array<ArModel>) {
        arrayOfArModels = newData
        notifyDataSetChanged()
    }
}