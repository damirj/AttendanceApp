package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.common.EmailPasswordAuth

class SignInUseCaseImpl(private val auth: EmailPasswordAuth) : SignInUseCase {
    override fun invoke(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit) {
        auth.signIn(email, password, checkIfTaskSuccess)
    }
}