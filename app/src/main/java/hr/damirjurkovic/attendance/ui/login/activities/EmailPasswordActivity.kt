package hr.damirjurkovic.attendance.ui.login.activities

import android.content.Intent
import android.text.TextUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.displayToast
import hr.damirjurkovic.attendance.ui.base.BaseActivity
import hr.damirjurkovic.attendance.ui.main.activities.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class EmailPasswordActivity : BaseActivity() {
    //TODO napraviti viewmodel i novu klasu za createacc i signIn
    private val auth: FirebaseAuth by inject()

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun setUpUi() {
        emailSignInButton.setOnClickListener {
            signIn(
                fieldEmail.text.toString(),
                fieldPassword.text.toString()
            )
        }
        emailCreateAccountButton.setOnClickListener {
            createAccount(
                fieldEmail.text.toString(),
                fieldPassword.text.toString()
            )
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        checkIfLoggedIn(currentUser)
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    checkIfLoggedIn(user)
                } else {
                    displayToast("Authentication failed.")
                }
            }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    checkIfLoggedIn(user)
                } else {
                    displayToast("Authentication failed.")
                }
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = fieldEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            fieldEmail.error = "Required."
            valid = false
        } else {
            fieldEmail.error = null
        }

        val password = fieldPassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            fieldPassword.error = "Required."
            valid = false
        } else {
            fieldPassword.error = null
        }

        return valid
    }

    private fun checkIfLoggedIn(user: FirebaseUser?) {
        if (user != null) {
            loginSuccess()
        }
    }

    private fun loginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}