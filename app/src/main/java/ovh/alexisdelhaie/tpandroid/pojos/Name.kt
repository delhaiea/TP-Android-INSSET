package ovh.alexisdelhaie.tpandroid.pojos

import com.google.gson.annotations.Expose

data class Name (
    @Expose
    val title: String,
    @Expose
    val first: String,
    @Expose
    val last: String
)