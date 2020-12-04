package ovh.alexisdelhaie.tpandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.viewmodels.FirebaseAuthViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: FirebaseAuthViewModel

    private var observerUser = Observer<FirebaseUser> {
        updateUser()
    }

    private var observerError = Observer<Int> {
        updateError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewModel = ViewModelProvider(this)[FirebaseAuthViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        viewModel.currentUser.observe(this, observerUser)
        viewModel.errorProcess.observe(this, observerError)
    }


    override fun onStop() {
        viewModel.currentUser.removeObserver(observerUser)
        viewModel.errorProcess.removeObserver(observerError)
        super.onStop()
    }

    fun onRegisterClick(v: View) {
        lockControls(true)
        val email = editRegisterUsername.text.toString()
        val p1 = editRegisterPassword.text.toString()
        val p2 = editRegisterPasswordAgain.text.toString()
        if (!email.isBlank() && !p1.isBlank() && !p2.isBlank()) {
            if (p1 == p2) {
                viewModel.register(email, p1);
            } else {
                updateError(18)
            }
        } else {
            updateError(19)
        }
    }

    private fun updateUser() {
        finish()
    }

    private fun updateError(err: Int) {
        lockControls(false)
        when (err) {
            10 -> errorRegister.text = getString(R.string.weak_password)
            15 -> errorRegister.text = getString(R.string.email_malformed)
            16 -> errorRegister.text = getString(R.string.user_collide)
            17 -> errorRegister.text = getString(R.string.wtf_error)
            18 -> errorRegister.text = getString(R.string.passwords_not_matches)
            19 -> errorRegister.text = getString(R.string.missing_field)
        }
        if (err != 9) {
            errorRegister.visibility = View.VISIBLE
        }
    }

    private fun lockControls(b: Boolean) {
        errorRegister.visibility = View.GONE
        editRegisterUsername.isEnabled = !b
        editRegisterPassword.isEnabled = !b
        editRegisterPasswordAgain.isEnabled = !b
        buttonRegister.isEnabled = !b
    }

}