package com.cultmost.android.ui.courses

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Dmitry Bochkov on 29.12.2020.
 */

class CoursesCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = CoursePageFragment()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt("object", position)
        }
        return fragment
    }
}