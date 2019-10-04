package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class GetAllCoursesUseCaseImpl(private val courseRepository: RepositoryInterface) : GetAllCoursesUseCase{

    override operator fun invoke(param: Unit) = courseRepository.getAllCourses()
}