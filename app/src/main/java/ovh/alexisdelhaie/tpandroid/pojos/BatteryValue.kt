package ovh.alexisdelhaie.tpandroid.pojos

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class BatteryValue (
        var voltage: Int,
        var percentage: Int
) {
        constructor() : this(-1, -1)
}