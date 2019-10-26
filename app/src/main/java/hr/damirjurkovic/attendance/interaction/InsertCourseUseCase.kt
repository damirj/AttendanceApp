package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface InsertCourseUseCase {

    operator fun invoke(course: Course)
}