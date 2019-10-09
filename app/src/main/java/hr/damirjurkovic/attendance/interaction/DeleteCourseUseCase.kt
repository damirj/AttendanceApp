package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface DeleteCourseUseCase : BaseUseCase<Course, Unit> {

    override operator fun invoke(course: Course)
}