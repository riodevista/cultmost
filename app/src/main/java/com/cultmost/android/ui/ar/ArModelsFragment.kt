package com.cultmost.android.ui.ar

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cultmost.MyApp
import com.cultmost.android.R
import com.cultmost.android.domain.ArModel
import com.cultmost.android.ui.ArModels.ArModelsAdapter
import kotlinx.android.synthetic.main.fragment_ar_models.*

class ArModelsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ar_models, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = ArModelsAdapter(Glide.with(this), MyApp.arModels).apply {
            setOnItemClickListener(object : ArModelsAdapter.OnItemClickListener {
                override fun onClick(position: Int, arModel: ArModel) {
                    openArModel(arModel)
                }

            })
        }
    }

    private fun openArModel(arModel: ArModel) {
        val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
        sceneViewerIntent.data =
            Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=${arModel.url}")
        sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
        startActivity(sceneViewerIntent)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ArModelsFragment().apply {
            }
    }
}