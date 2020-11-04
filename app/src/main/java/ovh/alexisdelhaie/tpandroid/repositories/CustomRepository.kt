package ovh.alexisdelhaie.tpandroid.repositories

import androidx.lifecycle.LiveData
import ovh.alexisdelhaie.tpandroid.MyApplication
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomRepository {

    private val dao = MyApplication.instance.applicationDatabase.mySecondDao()

    fun getDataFromDatabase(): LiveData<List<SimpleObject>> {
        return dao.selectAll()
    }

    suspend fun insert(myData: SimpleObject) = withContext(Dispatchers.IO) {
        dao.insert(myData)
    }

}