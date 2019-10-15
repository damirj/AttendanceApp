package hr.damirjurkovic.attendance.ui.course.list.presentation

import hr.damirjurkovic.attendance.interaction.*
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.course.list.view.CourseListEffect
import hr.damirjurkovic.attendance.ui.course.list.view.SignedOut


class CourseListViewModel(
    private val getAllCourses: GetAllCoursesUseCase,
    private val insertCourse: InsertCourseUseCase,
    private val deleteCourse: DeleteCourseUseCase,
    private val removeAllCourses: DeleteAllCoursesUseCase,
    private val signOut: SignOutUseCase

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

    fun signOutFromAcc() {
        signOut()
        _viewEffects.value = SignedOut
    }
}