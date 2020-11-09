package ovh.alexisdelhaie.tpandroid.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.annotation.Excludes
import com.google.gson.annotations.Expose

@Entity(tableName = "fake_people_table")
data class People (
    @Expose
    val gender: String,
    @Expose
    val name: Name,
    @Expose
    val location: Location,
    @Expose
    val email: String,
    @Expose
    val picture: Picture
){
    @PrimaryKey(autoGenerate = true)
    var peopleId: Long = 0
}
