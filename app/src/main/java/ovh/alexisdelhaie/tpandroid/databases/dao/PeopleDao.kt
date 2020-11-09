package ovh.alexisdelhaie.tpandroid.databases.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ovh.alexisdelhaie.tpandroid.pojos.People


@Dao
interface PeopleDao {

    @Query("SELECT * FROM fake_people_table")
    fun selectAll(): LiveData<List<People>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(myData: List<People>): List<Long>

    @Insert()
    fun insert(myData: People)

    @Query("DELETE FROM fake_people_table")
    fun deleteAll()
}
