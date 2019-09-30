package hr.damirjurkovic.dolaznost.persistence

import hr.damirjurkovic.dolaznost.Model.Course

interface RepositoryInterface {

    fun getAllCourses():List<Course>

    fun insertCourse(course: Course)

    fun updateAttendanceState(courseId: Int, leftHoursQuota: Double, wentHours: Double, leftHoursAll: Double, alarmState: Double)

    fun deleteCourse(course: Course)

    fun deleteAllCourses()

    fun getCourse(courseId: Int): Course
}