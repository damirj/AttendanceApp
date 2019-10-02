package hr.damirjurkovic.attendance.ui.course.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.CourseRepository

class CourseListViewModel : ViewModel() {
/*
    private val repository = CourseRepository()

    private val courses: MutableLiveData<MutableList<Course>> by lazy {
        MutableLiveData<MutableList<Course>>().also{
            loadCourses()
        }
    }

    fun getCourses(): LiveData<MutableList<Course>> {
        return courses
    }

    private fun loadCourses(): MutableList<Course>{
        return repository.getAllCourses()
    }
*/
}