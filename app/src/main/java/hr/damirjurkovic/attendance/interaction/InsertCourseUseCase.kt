package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface InsertCourseUseCase: BaseUseCase<Course, Course> {

    override operator fun invoke(course: Course): Course
}