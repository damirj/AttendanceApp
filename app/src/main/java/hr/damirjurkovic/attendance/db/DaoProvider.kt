package hr.damirjurkovic.attendance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hr.damirjurkovic.attendance.model.Course

@Database(entities = [Course::class], version = 1, exportSchema = false)
abstract class DaoProvider : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}