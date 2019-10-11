package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface GetAllCoursesUseCase {

    operator fun invoke(): MutableList<Course>

}