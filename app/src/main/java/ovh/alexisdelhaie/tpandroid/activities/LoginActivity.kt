package ovh.alexisdelhaie.tpandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import ovh.alexisdelhaie.tpandroid.R
import ovh.alexisdelhaie.tpandroid.viewmodels.FirebaseAuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: FirebaseAuthViewModel

    private var observerUser = Observer<FirebaseUser> {
        updateUser(it)
    }

    private var observerError = Observer<Int> {
        updateError(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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


    fun onNeedAnAccountButtonClick(v: View) {
        val i = Intent(this, RegisterActivity::class.java)
        finish()
        startActivity(i)
    }

    fun onLoginButtonClick(v: View) {
        lockControls(true)
        if (!editLoginPassword.text.isBlank() && !editLoginPassword.text.isBlank()) {
            viewModel.logIn(editLoginUsername.text.toString(), editLoginPassword.text.toString())
        } else {
            updateError(14)
        }
    }

    fun onLogoutButtonClick(v: View) {
        viewModel.logOut()
    }

    private fun updateUser(user: FirebaseUser) {
        editLoginUsername.visibility = View.GONE
        editLoginPassword.visibility = View.GONE
        buttonLogin.visibility = View.GONE
        buttonNeedAnAccount.visibility = View.GONE
        errorLogin.visibility = View.GONE
        lockControls(false)

        loginView.text = getString(R.string.connected_user, user.email)
        loginView.visibility = View.VISIBLE
        buttonDisconnect.visibility = View.VISIBLE
    }

    private fun updateError(err: Int) {
        lockControls(false)
        errorLogin.visibility = View.GONE
        when (err) {
            11 -> {
                errorLogin.text = getString(R.string.wrong_credential)
                errorLogin.visibility = View.VISIBLE
            }
            12 -> {
                errorLogin.text = getString(R.string.no_user_logged)
                errorLogin.visibility = View.VISIBLE
            }
            13 -> {
                editLoginUsername.visibility = View.VISIBLE
                editLoginPassword.visibility = View.VISIBLE
                buttonLogin.visibility = View.VISIBLE
                buttonNeedAnAccount.visibility = View.VISIBLE

                loginView.visibility = View.GONE
                buttonDisconnect.visibility = View.GONE
            }
            14 -> {
                errorLogin.text = getString(R.string.missing_field)
                errorLogin.visibility = View.VISIBLE
            }
        }
    }

    private fun lockControls(b: Boolean) {
        editLoginUsername.isEnabled = !b
        editLoginPassword.isEnabled = !b
        buttonLogin.isEnabled = !b
    }

}