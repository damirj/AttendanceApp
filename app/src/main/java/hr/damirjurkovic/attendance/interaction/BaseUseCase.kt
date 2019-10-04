package hr.damirjurkovic.attendance.interaction

interface BaseUseCase<T : Any, R: Any> {
    operator fun invoke(param: T): R
}