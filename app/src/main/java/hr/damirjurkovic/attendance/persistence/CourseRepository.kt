package hr.damirjurkovic.attendance.persistence

import hr.damirjurkovic.attendance.db.CourseDao
import hr.damirjurkovic.attendance.db.DaoProvider
import hr.damirjurkovic.attendance.model.Course

class CourseRepository(private val db: DaoProvider, private val courseDao: CourseDao) :
    RepositoryInterface {

    override fun getAllCourses(): MutableList<Course> = courseDao.getAllCourses()

    override fun insertCourse(course: Course): Course = courseDao.insertNewCourse(course)

    override fun updateCourse(course: Course): Course = courseDao.updateAttendanceState(course)

    override fun deleteCourse(course: Course) = courseDao.deleteCourse(course)

    override fun deleteAllCourses() = courseDao.deleteAllCourses()

    override fun getCourse(courseId: Int): Course = courseDao.getCourse(courseId)
}