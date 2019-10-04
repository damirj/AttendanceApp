package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class InsertCourseUseCaseImpl(private val courseRepository: RepositoryInterface): InsertCourseUseCase {

    override operator fun invoke(course: Course) = courseRepository.insertCourse(course)

}