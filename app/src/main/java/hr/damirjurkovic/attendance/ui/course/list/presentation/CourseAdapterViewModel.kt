package hr.damirjurkovic.attendance.ui.course.list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class CourseAdapterViewModel(private val repository: RepositoryInterface) : ViewModel() {

    private val _coursesLiveData = MutableLiveData<MutableList<Course>>()
    val coursesLiveData: LiveData<MutableList<Course>>
        get() = _coursesLiveData

    init {
        loadCourses()
    }

    fun deleteAllCourses(){
        repository.deleteAllCourses()
        _coursesLiveData.value?.clear()
    }

    fun deleteCourse(course: Course){
        repository.deleteCourse(course).also {
            _coursesLiveData.value?.remove(course)
        }
    }

    fun getSize(): Int {
        return _coursesLiveData.value?.size
    }

    fun loadCourses() {
        _coursesLiveData.value = repository.getAllCourses()
    }
}