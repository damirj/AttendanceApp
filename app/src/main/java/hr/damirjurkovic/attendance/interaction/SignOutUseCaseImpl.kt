package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.common.EmailPasswordAuth

class SignOutUseCaseImpl(private val auth: EmailPasswordAuth) : SignOutUseCase {
    override fun invoke() = auth.signOut()
}
