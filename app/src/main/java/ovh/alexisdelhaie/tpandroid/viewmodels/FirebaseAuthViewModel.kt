package ovh.alexisdelhaie.tpandroid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import ovh.alexisdelhaie.tpandroid.repositories.FirebaseAuthRepository

class FirebaseAuthViewModel: ViewModel() {

    private val repo: FirebaseAuthRepository by lazy { FirebaseAuthRepository() }
    var currentUser = MutableLiveData<FirebaseUser>()
    var errorProcess = MutableLiveData<Int>()

    init {
        currentUser = repo.currentUser
        errorProcess = repo.errorProcess
    }

    fun logIn(email: String, password: String) {
        repo.logIn(email, password)
    }

    fun register(email: String, password: String) {
        repo.register(email, password)
    }

    fun logOut() {
        repo.logOut()
    }

}