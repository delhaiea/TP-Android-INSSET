package ovh.alexisdelhaie.tpandroid.pojos

import com.google.gson.annotations.Expose

data class Street (
    @Expose
    val number: Int,
    @Expose
    val name: String
)