package hr.damirjurkovic.attendance.ui.login.view.activities

import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.displayToast
import hr.damirjurkovic.attendance.common.subscribe
import hr.damirjurkovic.attendance.ui.base.BaseActivity
import hr.damirjurkovic.attendance.ui.login.presentation.EmailPasswordViewModel
import hr.damirjurkovic.attendance.ui.login.view.EmailPasswordEffect
import hr.damirjurkovic.attendance.ui.login.view.Fail
import hr.damirjurkovic.attendance.ui.login.view.LoggedIn
import hr.damirjurkovic.attendance.ui.login.view.Success
import hr.damirjurkovic.attendance.ui.main.activities.startMainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmailPasswordActivity : BaseActivity() {

    private val viewModel: EmailPasswordViewModel by viewModel()

    override fun getLayoutRes(): Int = R.layout.activity_login

    override fun setUpUi() {
        setUpListeners()
        subscribeToData()
        viewModel.checkIfLoggedIn()
    }

    private fun setUpListeners() {
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

    private fun subscribeToData() {
        viewModel.viewEffects.subscribe(this, this::handleViewEffect)
    }

    private fun handleViewEffect(viewEffect: EmailPasswordEffect) {
        when (viewEffect) {
            is Success -> loginSuccess()
            is Fail -> loginFailed()
            is LoggedIn -> loginSuccess()
        }
    }

    private fun createAccount(email: String, password: String) {
        viewModel.createAccount(email, password)
    }

    private fun signIn(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    private fun loginSuccess() {
        startMainActivity(this)
        finish()
    }

    private fun loginFailed() {
        fieldEmail.error = getString(R.string.required)
        fieldEmail.error = null
        fieldPassword.error = getString(R.string.required)
        fieldPassword.error = null
        displayToast(getString(R.string.auth_failed))
    }
}