package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface DeleteCourseUseCase {

    operator fun invoke(course: Course)
}