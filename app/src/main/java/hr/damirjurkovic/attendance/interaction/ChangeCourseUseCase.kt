package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface ChangeCourseUseCase: BaseUseCase<Course, Course> {

    override operator fun invoke(course: Course): Course

}