package hr.damirjurkovic.attendance.interaction

interface SignInUseCase {

    operator fun invoke(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit)
}