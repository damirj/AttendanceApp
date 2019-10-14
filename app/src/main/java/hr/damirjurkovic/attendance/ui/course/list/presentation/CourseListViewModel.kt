package hr.damirjurkovic.attendance.ui.course.list.presentation

import hr.damirjurkovic.attendance.interaction.DeleteAllCoursesUseCase
import hr.damirjurkovic.attendance.interaction.DeleteCourseUseCase
import hr.damirjurkovic.attendance.interaction.GetAllCoursesUseCase
import hr.damirjurkovic.attendance.interaction.InsertCourseUseCase
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.course.list.view.CourseListEffect


class CourseListViewModel(
    private val getAllCourses: GetAllCoursesUseCase,
    private val insertCourse: InsertCourseUseCase,
    private val deleteCourse: DeleteCourseUseCase,
    private val removeAllCourses: DeleteAllCoursesUseCase
) :
    BaseViewModel<List<Course>, CourseListEffect>() {

    val courses = getAllCourses()

    fun addCourse(course: Course) {
        insertCourse(course)
    }

    fun deleteAllCourses() {
        removeAllCourses()
    }

    fun removeCourse(course: Course) {
        deleteCourse(course)
    }
}