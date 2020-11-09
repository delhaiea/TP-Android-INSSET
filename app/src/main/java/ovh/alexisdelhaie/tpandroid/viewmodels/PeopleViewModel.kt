package ovh.alexisdelhaie.tpandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ovh.alexisdelhaie.tpandroid.pojos.People
import ovh.alexisdelhaie.tpandroid.repositories.PeopleRepository

class PeopleViewModel: ViewModel() {

    private val peopleRepository: PeopleRepository by lazy { PeopleRepository() }
    var mChuckNorrisQuoteLiveData: LiveData<List<People>> = peopleRepository.selectAllPeople()


    fun fetchNewPeople() {
        viewModelScope.launch(Dispatchers.IO) {
            peopleRepository.fetchData()
        }
    }


    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            peopleRepository.deleteAll()
        }
    }

}