package hr.damirjurkovic.attendance.ui.course.details.presentation

import hr.damirjurkovic.attendance.interaction.ChangeCourseUseCase
import hr.damirjurkovic.attendance.interaction.GetCourseUseCase
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.base.Success
import hr.damirjurkovic.attendance.ui.course.details.view.CourseDetailsEffect
import hr.damirjurkovic.attendance.ui.course.details.view.DisableAttendanceBtn
import kotlin.properties.Delegates

class CourseDetailsViewModel(
    private val getCourseFromRepository: GetCourseUseCase,
    private val changeCourseAttendance: ChangeCourseUseCase
) : BaseViewModel<Course, CourseDetailsEffect>() {

    private var courseId by Delegates.notNull<Int>()
    private lateinit var course: Course

    fun setCourseID(courseId: Int) {
        this.courseId = courseId
        getCourse()
    }

    fun changeCourse(hours: Int, didAttend: Boolean) {
        course = changeCourseAttendance(course, hours, didAttend)
        _viewState.value = Success(course)
        checkIfCourseFinished()
    }

    private fun getCourse() {
        course = getCourseFromRepository(courseId)
        _viewState.value = Success(course)
        checkIfCourseFinished()
    }

    private fun checkIfCourseFinished() {
        if (course.leftHoursAll <= 0) _viewEffects.value = DisableAttendanceBtn
    }
}