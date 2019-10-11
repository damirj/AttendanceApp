package hr.damirjurkovic.attendance.persistence

import androidx.lifecycle.LiveData
import hr.damirjurkovic.attendance.db.CourseDao
import hr.damirjurkovic.attendance.db.DaoProvider
import hr.damirjurkovic.attendance.model.Course

class CourseRepository(private val db: DaoProvider, private val courseDao: CourseDao) :
    RepositoryInterface {

    override fun getAllCourses(): LiveData<MutableList<Course>> = courseDao.getAllCourses()

    override fun insertCourse(course: Course): Course = courseDao.insertNewCourse(course)

    override fun updateCourse(course: Course) = courseDao.updateCourse(course)

    override fun deleteCourse(course: Course) = courseDao.deleteCourse(course)

    override fun deleteAllCourses() = courseDao.deleteAllCourses()

    override fun getCourse(courseId: Int): LiveData<Course> = courseDao.getCourse(courseId)
}