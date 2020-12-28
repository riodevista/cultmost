package com.cultmost

import android.app.Application
import com.cultmost.android.R
import com.cultmost.android.domain.Course

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
    }

    override fun onCreate() {
        super.onCreate()
    }
}