package ovh.alexisdelhaie.tpandroid.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import ovh.alexisdelhaie.tpandroid.databases.dao.SimpleObjectDao
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject

@Database(
    entities = [
        SimpleObject::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {

    abstract fun mySecondDao() : SimpleObjectDao

}