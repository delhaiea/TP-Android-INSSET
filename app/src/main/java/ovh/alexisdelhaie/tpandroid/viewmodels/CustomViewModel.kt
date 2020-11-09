package ovh.alexisdelhaie.tpandroid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ovh.alexisdelhaie.tpandroid.pojos.SimpleObject
import ovh.alexisdelhaie.tpandroid.repositories.CustomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CustomViewModel: ViewModel() {

    private val repository: CustomRepository by lazy { CustomRepository() }
    var mutable: LiveData<List<SimpleObject>> = repository.getDataFromDatabase()

    fun insertData(text: String, drawableId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(SimpleObject(null, text, drawableId, "", Date()))
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun getSize(): Int? {
        return mutable.value?.size;
    }
}