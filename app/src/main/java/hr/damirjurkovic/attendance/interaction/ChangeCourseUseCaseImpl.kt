package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.CourseRepository
import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class ChangeCourseUseCaseImpl(private val courseRepository: RepositoryInterface): ChangeCourseUseCase {

    override operator fun invoke(course: Course) = courseRepository.updateCourse(course)

}