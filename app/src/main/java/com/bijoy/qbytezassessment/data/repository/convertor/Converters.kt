package com.bijoy.qbytezassessment.data.repository.convertor

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        if (value.isEmpty()) return emptyList()
        return value.split(",").map { it.toInt() }
    }
}