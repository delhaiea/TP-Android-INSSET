package ovh.alexisdelhaie.tpandroid.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ovh.alexisdelhaie.tpandroid.databases.dao.PeopleDao
import ovh.alexisdelhaie.tpandroid.databases.dao.SimpleObjectDao
import ovh.alexisdelhaie.tpandroid.pojos.People
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject

@Database(
    entities = [
        SimpleObject::class,
        People::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {

    abstract fun mySecondDao() : SimpleObjectDao
    abstract fun getPeopleDao(): PeopleDao

}