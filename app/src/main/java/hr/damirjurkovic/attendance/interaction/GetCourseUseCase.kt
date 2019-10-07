package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface GetCourseUseCase: BaseUseCase<Int, Course> {

    override operator fun invoke(courseId: Int): Course

}