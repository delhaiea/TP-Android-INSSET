package ovh.alexisdelhaie.tpandroid.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "SimpleObject")
data class SimpleObject(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val text : String,
    val drawable: Int,
    val attribut: String,
    val created: Date
)
