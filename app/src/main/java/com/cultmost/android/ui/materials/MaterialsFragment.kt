package com.cultmost.android.ui.materials

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cultmost.MyApp
import com.cultmost.android.R
import com.cultmost.android.domain.Material
import kotlinx.android.synthetic.main.fragment_course_page.*

class MaterialsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_materials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = GridLayoutManager(context, 2)
        recycler_view.adapter =
            MaterialsAdapter(
                Glide.with(context!!), MyApp.materials
            )
                .apply {
                    setOnItemClickListener(object : MaterialsAdapter.OnItemClickListener {
                        override fun onClick(position: Int, material: Material) {
                            openMaterial(position, material)
                        }
                    })
                }
    }

    private fun openMaterial(position: Int, material: Material) {
        startActivity(Intent(context, MaterialItemActivity::class.java).apply { putExtra("position", position) })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MaterialsFragment().apply {
            }
    }
}