package hr.damirjurkovic.attendance.ui.course.details.presentation

import hr.damirjurkovic.attendance.interaction.ChangeCourseUseCase
import hr.damirjurkovic.attendance.interaction.GetCourseUseCase
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.base.Success
import hr.damirjurkovic.attendance.ui.course.details.view.CourseDetailsEffect
import kotlin.properties.Delegates

class CourseDetailsViewModel(private val getCourseFromRepository: GetCourseUseCase, private val changeCourseAttendance: ChangeCourseUseCase) : BaseViewModel<Course, CourseDetailsEffect>() {

    private var courseId by Delegates.notNull<Int>()
    private lateinit var course: Course

    fun setCourseID(courseId: Int) {
        this.courseId = courseId
        getCourse()
    }

    fun changeCourse(hours: Int, didAttend: Boolean) {
        //TODO jel treba ovo sve prebaciti u ChangeCourseUseCaseImpl? BasaUseCase mi prima samo 1 parametar ovdje mi trebaju 3 (course, hours, didAttend)
        course.run {
            val hoursReal = if (hours > leftHoursAll) leftHoursAll.toInt() else hours
            if (didAttend) {
                val leftHoursQuota =
                    if (leftHoursQuota - hoursReal > 0) leftHoursQuota - hoursReal else 0.0
                val wentHours = this.wentHours + hoursReal
                val leftHoursAll = this.leftHoursAll - hoursReal
                val alarmState = this.leftHoursAll - this.leftHoursQuota
                val course = this.copy(
                    leftHoursQuota = leftHoursQuota,
                    wentHours = wentHours,
                    leftHoursAll = leftHoursAll,
                    alarmState = alarmState
                )
                _viewState.value = Success(changeCourseAttendance(course))
            } else {
                val leftHoursAll = this.leftHoursAll - hoursReal
                val alarmState = this.leftHoursAll - this.leftHoursQuota - hoursReal
                val course = this.copy(
                    leftHoursQuota = leftHoursQuota,
                    wentHours = wentHours,
                    leftHoursAll = leftHoursAll,
                    alarmState = alarmState
                )
                _viewState.value = Success(changeCourseAttendance(course))
            }
        }
    }

    private fun getCourse(){
        course = getCourseFromRepository(courseId)
        _viewState.value = Success(course)
    }
}