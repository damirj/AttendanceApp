package hr.damirjurkovic.attendance.ui.course.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class CourseDetailsViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _coursesLiveData = MutableLiveData<MutableList<Course>>()
    val coursesLiveData: LiveData<MutableList<Course>>
        get() = _coursesLiveData

    init {
        loadCourses()
    }

    fun getCourse(courseId: Int): Course {
        return _coursesLiveData.value?.get(courseId)
    }

    fun updateAttendanceDetails(
        courseId: Int,
        leftHoursQuota: Double,
        wentHours: Double,
        leftHoursAll: Double,
        alarmState: Double
    ) {
        repository.updateAttendanceState(
            courseId,
            leftHoursQuota,
            wentHours,
            leftHoursAll,
            alarmState
        )
    }

    fun loadCourses() {
        _coursesLiveData.value = repository.getAllCourses()
    }


}