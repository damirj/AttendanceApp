package hr.damirjurkovic.attendance.persistence

import androidx.lifecycle.LiveData
import hr.damirjurkovic.attendance.model.Course

interface RepositoryInterface {

    fun getAllCourses(): LiveData<MutableList<Course>>

    fun insertCourse(course: Course): Course

    fun updateCourse(course: Course)

    fun deleteCourse(course: Course)

    fun deleteAllCourses()

    fun getCourse(courseId: Int): LiveData<Course>
}