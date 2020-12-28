package com.cultmost.android.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.cultmost.android.R
import com.cultmost.android.domain.Course
import kotlinx.android.synthetic.main.holder_main_screen_course.view.*

/**
 * Created by Dmitry Bochkov on 30.07.2020.
 */

class CoursesAdapter(
    private val glide: RequestManager,
    private var arrayOfCourses: Array<Course>
) :
    RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_main_screen_course, parent, false) as View
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = arrayOfCourses[position]
        holder.view.title.text = item.title
        holder.view.description.text = item.description
//        holder.view.date.text = item.dae
        glide.load(item.image).centerCrop().into(holder.view.image)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(
                position,
                arrayOfCourses[position]
            )
        }
    }

    override fun getItemCount() = arrayOfCourses.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onClick(position: Int, course: Course)
    }

    fun setData(newData: Array<Course>) {
        arrayOfCourses = newData
        notifyDataSetChanged()
    }
}