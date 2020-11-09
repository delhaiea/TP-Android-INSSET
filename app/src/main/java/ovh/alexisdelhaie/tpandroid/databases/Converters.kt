package ovh.alexisdelhaie.tpandroid.databases

import androidx.room.TypeConverter
import com.google.gson.Gson
import ovh.alexisdelhaie.tpandroid.pojos.Location
import ovh.alexisdelhaie.tpandroid.pojos.Name
import ovh.alexisdelhaie.tpandroid.pojos.Picture
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun pictureToJson(obj: Picture?): String? {
        return Gson().toJson(obj)
    }

    @TypeConverter
    fun pictureFromJson(obj: String?): Picture? {
        return Gson().fromJson(obj, Picture::class.java)
    }

    @TypeConverter
    fun locationToJson(obj: Location?): String? {
        return Gson().toJson(obj)
    }

    @TypeConverter
    fun locationFromJson(obj: String?): Location? {
        return Gson().fromJson(obj, Location::class.java)
    }

    @TypeConverter
    fun nameToJson(obj: Name?): String? {
        return Gson().toJson(obj)
    }

    @TypeConverter
    fun nameFromJson(obj: String?): Name? {
        return Gson().fromJson(obj, Name::class.java)
    }
}