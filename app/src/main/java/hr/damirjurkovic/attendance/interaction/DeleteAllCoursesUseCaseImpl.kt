package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class DeleteAllCoursesUseCaseImpl(private val courseRepository: RepositoryInterface) :
    DeleteAllCoursesUseCase {

    override fun invoke() = courseRepository.deleteAllCourses()

}