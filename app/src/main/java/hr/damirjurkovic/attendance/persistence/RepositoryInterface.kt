package hr.damirjurkovic.attendance.persistence

import hr.damirjurkovic.attendance.model.Course

interface RepositoryInterface {

    fun getAllCourses(): MutableList<Course>

    fun insertCourse(course: Course): Course

    fun updateCourse(course: Course): Course

    fun deleteCourse(course: Course)

    fun deleteAllCourses()

    fun getCourse(courseId: Int): Course
}