package hr.damirjurkovic.attendance.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import hr.damirjurkovic.attendance.model.Course

@Dao
interface CourseDao {

    @Query("SELECT * FROM Course")
    fun getAllCourses(): MutableList<Course>

    @Insert(onConflict = IGNORE)
    fun insertCourse(course: Course): Long

    @Transaction
    fun insertNewCourse(course: Course): Course {
        insertCourse(course)
        return getLastCourse()
    }

    @Transaction
    fun updateAttendanceState(course: Course): Course {
        updateCourse(course)
        return getCourse(course.courseDbId ?: 0)
    }

    @Update
    fun updateCourse(course: Course)

    @Delete
    fun deleteCourse(course: Course)

    @Query("DELETE from Course")
    fun deleteAllCourses()

    @Query("SELECT * FROM Course WHERE courseDbId = :courseId")
    fun getCourse(courseId: Int): Course

    @Query("SELECT * FROM Course ORDER BY courseDbId DESC LIMIT 1")
    fun getLastCourse(): Course

}