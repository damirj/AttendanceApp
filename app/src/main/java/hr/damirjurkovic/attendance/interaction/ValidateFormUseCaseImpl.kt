package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.common.EmailPasswordAuth

class ValidateFormUseCaseImpl(private val auth: EmailPasswordAuth) : ValidateFormUseCase {
    override fun invoke(email: String, password: String) = auth.validateForm(email, password)
}