package com.cultmost.android.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.cultmost.android.R
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_courses.*

class CoursesFragment : Fragment() {

    private lateinit var coursesCollectionAdapter: CoursesCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CoursesFragment().apply {
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabLayout = tab_layout
        viewPager = pager
        viewPager.adapter = CoursesCollectionAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Все"
                1 -> "История"
                2 -> "География"
                3 -> "Искусство"
                else -> ""
            }
        }.attach()
    }
}