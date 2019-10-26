package hr.damirjurkovic.attendance.interaction

interface CreateAccountUseCase {

    operator fun invoke(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit)
}