package hr.damirjurkovic.attendance.interaction

interface ValidateFormUseCase {

    operator fun invoke(email: String, password: String): Boolean
}