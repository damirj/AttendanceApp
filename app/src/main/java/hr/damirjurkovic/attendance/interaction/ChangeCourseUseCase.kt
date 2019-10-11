package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface ChangeCourseUseCase {

     operator fun invoke(course: Course, hours: Int, didAttend: Boolean)

}