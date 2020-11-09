package ovh.alexisdelhaie.tpandroid.retrofit

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ResultDeserializer<T>: JsonDeserializer<T> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T {
        val content = json?.asJsonObject?.get("results")?.asJsonArray?.get(0)
        return Gson().fromJson(content, typeOfT);
    }

}