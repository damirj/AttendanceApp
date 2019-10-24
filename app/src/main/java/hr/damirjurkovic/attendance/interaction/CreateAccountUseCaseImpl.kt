package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.common.EmailPasswordAuth

class CreateAccountUseCaseImpl(private val auth: EmailPasswordAuth) : CreateAccountUseCase {

    override fun invoke(
        email: String,
        password: String,
        checkIfTaskSuccess: (Boolean) -> Unit
    ) {
        auth.createAccount(email, password, checkIfTaskSuccess)
    }

}