package com.cultmost.android.ui.courses

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cultmost.MyApp
import com.cultmost.android.R
import com.cultmost.android.domain.Course
import kotlinx.android.synthetic.main.fragment_course_page.*


/**
 * Created by Dmitry Bochkov on 29.12.2020.
 */

class CoursePageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_course_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler_view.layoutManager = GridLayoutManager(context, 2)
        arguments?.takeIf { it.containsKey("object") }?.apply {
            val position = getInt("object")
            recycler_view.adapter =
                CoursesAdapter(Glide.with(context!!), MyApp.coursesArray.filter {
                    when (position) {
                        0 -> true
                        1 -> it.category == Course.HISTORY
                        2 -> it.category == Course.GEOGRAPHY
                        3 -> it.category == Course.ART
                        else -> true
                    }
                }.toTypedArray()).apply {
                    setOnItemClickListener(object : CoursesAdapter.OnItemClickListener {
                        override fun onClick(position: Int, course: Course) {
                            openUrl(course.url)
                        }
                    })
                }
        }
    }

    private fun openUrl(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context!!, Uri.parse(url))
    }
}