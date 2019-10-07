package hr.damirjurkovic.attendance.ui.course.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface


class CourseListViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _coursesLiveData = MutableLiveData<MutableList<Course>>()
    val coursesLiveData: LiveData<MutableList<Course>>
        get() = _coursesLiveData

    init {
        loadCourses()
    }

    fun addCourse(course: Course) {
        repository.insertCourse(course).also {
            val courses = _coursesLiveData.value
            courses?.add(course)
            _coursesLiveData.value = courses
        }
    }

    fun deleteAllCourses() {
        repository.deleteAllCourses()
        _coursesLiveData.value?.clear()
    }

    fun deleteCourse(course: Course) {
        repository.deleteCourse(course).also {
            _coursesLiveData.value?.remove(course)
        }
    }

    private fun loadCourses() {
        _coursesLiveData.value = repository.getAllCourses()
    }

    fun refresh() {
        loadCourses()
    }
}