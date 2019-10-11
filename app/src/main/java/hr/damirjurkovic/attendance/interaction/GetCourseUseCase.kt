package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface GetCourseUseCase {

    operator fun invoke(courseId: Int): Course

}