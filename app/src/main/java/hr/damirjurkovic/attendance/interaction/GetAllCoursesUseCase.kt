package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course

interface GetAllCoursesUseCase: BaseUseCase<Unit, MutableList<Course>> {

    override operator fun invoke(param: Unit): MutableList<Course>

}