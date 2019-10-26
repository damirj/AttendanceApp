package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.common.EmailPasswordAuth

class CheckLoginUseCaseImpl(private val auth: EmailPasswordAuth) : CheckLoginUseCase {

    override fun invoke() = auth.checkIfLoggedIn()

}