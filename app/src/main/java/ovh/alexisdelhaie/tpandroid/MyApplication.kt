package ovh.alexisdelhaie.tpandroid

import android.app.Application
import androidx.room.Room
import ovh.alexisdelhaie.tpandroid.databases.Database

class MyApplication : Application() {

    ////////////////////////////////////////////
    // Attribute ///////////////////////////////
    ////////////////////////////////////////////

    companion object {
        lateinit var instance: MyApplication
    }

    ////////////////////////////////////////////
    // Managing Lifecycle //////////////////////
    ////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val applicationDatabase: Database by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "SimpleObjectDatabase"
        ).fallbackToDestructiveMigration().build()
    }
}
