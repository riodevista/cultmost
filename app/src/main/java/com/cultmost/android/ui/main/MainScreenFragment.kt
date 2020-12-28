package com.cultmost.android.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.cultmost.MyApp
import com.cultmost.android.R
import com.cultmost.android.domain.Course
import com.cultmost.android.ui.MainScreenActivity
import kotlinx.android.synthetic.main.fragment_main_screen.*


class MainScreenFragment : Fragment() {

    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        glide = Glide.with(this)

        glide.load(R.drawable.banner_new).centerCrop().into(banner_new)

        courses_recycler_view.layoutManager = LinearLayoutManager(context)
        courses_recycler_view.adapter = CoursesAdapter(
            glide,
            MyApp.coursesArray
        ).apply {
            setOnItemClickListener(object : CoursesAdapter.OnItemClickListener {
                override fun onClick(position: Int, course: Course) {
                    openUrl(course.url)
                }

            })
        }

        courses_header.setOnClickListener {
            (activity as MainScreenActivity).showCourses()
        }

        cultmost_button.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://cultmost.ru/")
                )
            )
        }
    }

    private fun openUrl(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context!!, Uri.parse(url))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainScreenFragment().apply {
            }
    }
}