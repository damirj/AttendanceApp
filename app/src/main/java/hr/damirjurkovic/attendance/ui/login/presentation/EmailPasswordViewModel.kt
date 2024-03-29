package hr.damirjurkovic.attendance.ui.login.presentation

import com.example.domain.interaction.authentication.CheckLoginUseCase
import com.example.domain.interaction.authentication.CreateAccountUseCase
import com.example.domain.interaction.authentication.SignInUseCase
import com.example.domain.interaction.authentication.ValidateFormUseCase
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.login.view.EmailPasswordEffect
import hr.damirjurkovic.attendance.ui.login.view.Fail
import hr.damirjurkovic.attendance.ui.login.view.LoggedIn
import hr.damirjurkovic.attendance.ui.login.view.Success

class EmailPasswordViewModel(
    private val createNewAccount: CreateAccountUseCase,
    private val validateInputForm: ValidateFormUseCase,
    private val signInAccount: SignInUseCase,
    private val checkIfLogged: CheckLoginUseCase
) :
    BaseViewModel<Unit, EmailPasswordEffect>() {

    fun createAccount(email: String, password: String) {
        if (validateForm(email, password)) createNewAccount(
            email,
            password
        ) { checkIfTaskSuccess(it) }
    }

    fun signIn(email: String, password: String) {
        if (validateForm(email, password)) signInAccount(
            email,
            password
        ) { checkIfTaskSuccess(it) }
    }

    fun checkIfLoggedIn() {
        if (checkIfLogged()) _viewEffects.value = LoggedIn
    }

    private fun validateForm(email: String, password: String) = validateInputForm(email, password)

    private fun checkIfTaskSuccess(isSuccess: Boolean) {
        if (isSuccess) _viewEffects.value = Success else _viewEffects.value = Fail
    }
}