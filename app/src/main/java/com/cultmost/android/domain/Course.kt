package com.cultmost.android.domain

/**
 * Created by Dmitry Bochkov on 28.12.2020.
 */



data class Course (val title: String, val description: String, val image: Int, val url: String, val category: Int, val count: String) {
    companion object {
        const val HISTORY = 1
        const val GEOGRAPHY = 2
        const val ART = 3
    }
}