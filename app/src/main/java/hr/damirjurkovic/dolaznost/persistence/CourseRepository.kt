package hr.damirjurkovic.dolaznost.persistence

import hr.damirjurkovic.dolaznost.AttendanceApp
import hr.damirjurkovic.dolaznost.Model.Course
import hr.damirjurkovic.dolaznost.db.CourseDao
import hr.damirjurkovic.dolaznost.db.DaoProvider

class CourseRepository: RepositoryInterface{

    private var db = DaoProvider.getInstance(AttendanceApp.getAppContext())
    private var courseDao: CourseDao = db.courseDao()

    override fun getAllCourses(): MutableList<Course> = courseDao.getAllCourses()

    override fun insertCourse(course: Course) {
        courseDao.insertCourse(course)
    }

    override fun updateAttendanceState(courseId: Int, leftHoursQuota: Double, wentHours: Double, leftHoursAll: Double, alarmState: Double) {
        courseDao.updateAttendanceState(courseId, leftHoursQuota, wentHours, leftHoursAll, alarmState)
    }

    override fun deleteCourse(course: Course) {
        courseDao.deleteCourse(course)
    }

    override fun deleteAllCourses() {
        courseDao.deleteAllCourses()
    }

    override fun getCourse(courseId: Int): Course = courseDao.getCourse(courseId)

}