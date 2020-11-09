package ovh.alexisdelhaie.tpandroid.pojos

import com.google.gson.annotations.Expose

data class Location (
    @Expose
    val street: Street,
    @Expose
    val city: String,
    @Expose
    val state: String,
    @Expose
    val country: String,
    @Expose
    val postcode: Int,
    @Expose
    val coordinates: Coordinates,
    @Expose
    val timezone: Timezone
)