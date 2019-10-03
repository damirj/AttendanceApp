package hr.damirjurkovic.attendance.ui.course.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface
import kotlin.properties.Delegates

class CourseDetailsViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _courseLiveData = MutableLiveData<Course>()
    val courseLiveData: LiveData<Course>
        get() = _courseLiveData

    private var courseId by Delegates.notNull<Int>()
    private lateinit var course: Course

    fun setCourseID(courseId: Int) {
        this.courseId = courseId
        loadCourse()
    }

    fun changeCourse(hours: Int, didAttend: Boolean) {
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
                updateCourse(course)
            } else {
                val leftHoursAll = this.leftHoursAll - hoursReal
                val alarmState = this.leftHoursAll - this.leftHoursQuota - hoursReal
                val course = this.copy(
                    leftHoursQuota = leftHoursQuota,
                    wentHours = wentHours,
                    leftHoursAll = leftHoursAll,
                    alarmState = alarmState
                )
                updateCourse(course)
            }
        }
    }

    private fun updateCourse(course: Course) {
        _courseLiveData.value = repository.updateCourse(course)
    }

    private fun loadCourse() {
        course = repository.getCourse(courseId)
        _courseLiveData.value = course
    }
}