package com.cultmost.android.domain

/**
 * Created by Dmitry Bochkov on 28.12.2020.
 */


data class Material(
    val title: String,
    val description: String,
    val image: Int,
    val count: String,
    var items: Array<Item>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Material

        if (title != other.title) return false
        if (description != other.description) return false
        if (image != other.image) return false
        if (count != other.count) return false
        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + image
        result = 31 * result + count.hashCode()
        result = 31 * result + items.contentHashCode()
        return result
    }

}