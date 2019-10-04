package hr.damirjurkovic.attendance.ui.course.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hr.damirjurkovic.attendance.interaction.DeleteAllCoursesUseCase
import hr.damirjurkovic.attendance.interaction.DeleteCourseUseCase
import hr.damirjurkovic.attendance.interaction.GetAllCoursesUseCase
import hr.damirjurkovic.attendance.interaction.InsertCourseUseCase
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.base.Success
import hr.damirjurkovic.attendance.ui.course.list.view.CourseListEffect


class CourseListViewModel(private val getAllCourses: GetAllCoursesUseCase, private val instertCourse: InsertCourseUseCase, private val deleteCourse: DeleteCourseUseCase, private val deleteAllCourses: DeleteAllCoursesUseCase) : BaseViewModel<MutableList<Course>, CourseListEffect>() {

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