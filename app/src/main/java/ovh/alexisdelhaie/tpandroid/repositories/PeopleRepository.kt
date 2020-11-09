package ovh.alexisdelhaie.tpandroid.repositories

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ovh.alexisdelhaie.tpandroid.MyApplication
import ovh.alexisdelhaie.tpandroid.pojos.People
import ovh.alexisdelhaie.tpandroid.retrofit.RetrofitBuilder
import java.lang.Exception

class PeopleRepository {

    private val peopleDao = MyApplication.instance.applicationDatabase.getPeopleDao()


    fun selectAllPeople() : LiveData<List<People>> {
        return peopleDao.selectAll()
    }


    private suspend fun insertPeople(people: People) = withContext(Dispatchers.IO) {
        peopleDao.insert(people)
    }


    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        peopleDao.deleteAll()
    }


    suspend fun fetchData() {
        try {
            insertPeople(RetrofitBuilder().getPeople().getRandomPeople())
        } catch (e: Exception) {

        }
    }

}