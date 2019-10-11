package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class GetCourseUseCaseImpl(private val courseRepository: RepositoryInterface): GetCourseUseCase {

    override operator fun invoke(courseId: Int) = courseRepository.getCourse(courseId)

}