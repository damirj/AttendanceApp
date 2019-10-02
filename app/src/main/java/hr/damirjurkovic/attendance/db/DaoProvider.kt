package hr.damirjurkovic.attendance.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.damirjurkovic.attendance.common.DATABASE_NAME
import hr.damirjurkovic.attendance.model.Course

@Database(entities = [Course::class], version = 1)
abstract class DaoProvider: RoomDatabase() {
    abstract fun courseDao(): CourseDao

    companion object {
        private var instance: DaoProvider? = null

        fun getInstance(context: Context): DaoProvider {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DaoProvider::class.java,
                    DATABASE_NAME
                ).allowMainThreadQueries().build()
            }
            return instance as DaoProvider
        }
    }
}

//TODO izbaciti allowMainThreadQueries
