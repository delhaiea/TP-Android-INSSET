package ovh.alexisdelhaie.tpandroid.repositories

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthRepository {

    private var firebaseAuth: FirebaseAuth = Firebase.auth

    var currentUser = MutableLiveData<FirebaseUser>()
    var errorProcess = MutableLiveData<Int>()

    init {
        if (firebaseAuth.currentUser != null) {
            currentUser.postValue(firebaseAuth.currentUser)
        } else {
            errorProcess.postValue(9)
        }
    }

    fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                if (firebaseAuth.currentUser != null) {
                    currentUser.postValue(firebaseAuth.currentUser)
                } else {
                    errorProcess.postValue(9)
                }
            } else {
                if (it.exception != null) {
                    when (it.exception!!::class.java) {
                        FirebaseAuthWeakPasswordException::class.java -> {
                            errorProcess.postValue(10)
                        }
                        FirebaseAuthInvalidCredentialsException::class.java -> {
                            errorProcess.postValue(15)
                        }
                        FirebaseAuthUserCollisionException::class.java -> {
                            errorProcess.postValue(16)
                        }
                        else -> {
                            errorProcess.postValue(17)
                        }
                    }
                }
            }
        }
    }

    fun logIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                currentUser.postValue(firebaseAuth.currentUser)
            } else {
                errorProcess.postValue(11)
            }
        }
    }

    fun logOut() {
        if (firebaseAuth.currentUser != null) {
            firebaseAuth.signOut()
            errorProcess.postValue(13)
        } else {
            errorProcess.postValue(12)
        }
    }

}