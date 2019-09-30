package hr.damirjurkovic.dolaznost.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import hr.damirjurkovic.dolaznost.Model.Course

@Dao
interface CourseDao {

    @Query("SELECT * FROM Course")
    fun getAllCourses(): MutableList<Course>

    @Insert(onConflict = IGNORE)
    fun insertCourse(course: Course)

    @Query("UPDATE Course SET leftHoursQuota = :leftHoursQuota, wentHours = :wentHours, leftHoursAll = :leftHoursAll, alarmState = :alarmState WHERE courseDbId = :courseId")
    fun updateAttendanceState(courseId: Int, leftHoursQuota: Double, wentHours: Double, leftHoursAll: Double, alarmState: Double)

    @Delete()
    fun deleteCourse(course: Course)

    @Query("DELETE from Course")
    fun deleteAllCourses()

    @Query("SELECT * FROM Course WHERE courseDbId = :courseId")
    fun getCourse(courseId: Int): Course

}