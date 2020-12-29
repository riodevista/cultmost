package com.cultmost

import android.app.Application
import com.cultmost.android.R
import com.cultmost.android.domain.ArModel
import com.cultmost.android.domain.Course
import com.cultmost.android.domain.Item
import com.cultmost.android.domain.Material

/**
 * Created by Dmitry Bochkov on 29.12.2020.
 */

class MyApp : Application() {

    companion object {
        val coursesArray: Array<Course> = arrayOf(
            Course("С востока на запад", "Виртуальное путешествие по миру", R.drawable.cover_geography, "http://cultmost.com/geo", Course.GEOGRAPHY, "5 занятий"),
            Course("Тайна сцены", "Курс о театре и сценографии", R.drawable.cover_history, "http://cultmost.com/teatr", Course.ART, "6 занятий"),
            Course("Импрессионизм на кончиках пальцев", "Для детей 7+ и взрослых", R.drawable.cover_art, "http://cultmost.com/ikp", Course.ART, "8 занятий")
        )

        val materials: Array<Material> = arrayOf(
            Material("Как устроен балет", "Описание категории", R.drawable.cover_balet, "3 выпуска",
                arrayOf(
                    Item("Как создавалась Шахерезада", "43:58", "18 ноября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I"),
                    Item("Как создавалась Елизавета", "14:58", "17 ноября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I"),
                    Item("Секреты Артона", "11:37", "16 октября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I"))),
            Material("Как устроена вселенная", "Описание категории", R.drawable.cover_universe, "2 выпуска",
                arrayOf(
                    Item("Как создавалась Шахерезада", "43:58", "18 ноября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I"),
                    Item("Как создавалась Елизавета", "14:58", "17 ноября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I")
            )),
            Material("Монтана блэк состав", "Описание категории", R.drawable.cover_montata, "2 выпуска",
                arrayOf(
                    Item("Как создавалась Шахерезада", "43:58", "18 ноября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I"),
                    Item("Как создавалась Елизавета", "14:58", "17 ноября 2019", "https://www.youtube.com/watch?v=p6n4-Mf2w-I")
                )
            )
        )

        val arModels: Array<ArModel> = arrayOf(
            ArModel("Статуя Давида", "Описание статуи Давида", "https://raw.githubusercontent.com/riodevista/ar_sceneviewer/main/david_model.gltf", R.drawable.david_preview),
            ArModel("Египетская пирамида", "Описание египетской пирамиды", "https://raw.githubusercontent.com/riodevista/ar_sceneviewer/main/david_model.gltf", R.drawable.pyramid_preview),
            ArModel("Еловая ветка", "Описание еловой ветки", "https://raw.githubusercontent.com/riodevista/ar_sceneviewer/main/david_model.gltf", R.drawable.tree_preview)

        )
    }

    override fun onCreate() {
        super.onCreate()
    }
}