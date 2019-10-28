package hr.damirjurkovic.attendance.ui.course.details.presentation

import androidx.lifecycle.LiveData
import com.example.domain.interaction.course.ChangeCourseUseCase
import com.example.domain.interaction.course.GetCourseUseCase
import com.example.domain.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.course.details.view.CourseDetailsEffect
import hr.damirjurkovic.attendance.ui.course.details.view.DisableAttendanceBtn
import kotlin.properties.Delegates

class CourseDetailsViewModel(
    private val getCourseFromRepository: GetCourseUseCase,
    private val changeCourseAttendance: ChangeCourseUseCase
) : BaseViewModel<Course, CourseDetailsEffect>() {

    private var courseId by Delegates.notNull<Int>()
    lateinit var course: LiveData<Course>

    fun changeCourse(hours: Int, didAttend: Boolean) {
        course.value?.let {
            changeCourseAttendance(it, hours, didAttend)
        }
        checkIfCourseFinished()
    }

    fun getCourse(id: Int) {
        courseId = id
        course = getCourseFromRepository(courseId)
        checkIfCourseFinished()
    }

    private fun checkIfCourseFinished() {
        course.value?.run {
            if (leftHoursAll <= 0) _viewEffects.value = DisableAttendanceBtn
        }
    }
}