package ovh.alexisdelhaie.tpandroid.databases.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject


@Dao
interface SimpleObjectDao {

    @Query("SELECT * FROM SimpleObject")
    fun selectAll(): LiveData<List<SimpleObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(myData: List<SimpleObject>): List<Long>

    @Insert()
    fun insert(myData: SimpleObject)

    @Query("DELETE FROM SimpleObject")
    fun deleteAll()
}
