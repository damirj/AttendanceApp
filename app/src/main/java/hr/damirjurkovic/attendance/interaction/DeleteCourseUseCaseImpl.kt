package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class DeleteCourseUseCaseImpl(private val courseRepository: RepositoryInterface): DeleteCourseUseCase {

    override operator fun invoke(course: Course) = courseRepository.deleteCourse(course)
}