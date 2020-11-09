package ovh.alexisdelhaie.tpandroid.pojos

import com.google.gson.annotations.Expose

data class Timezone (
    @Expose
    val offset: String,
    @Expose
    val description: String
)