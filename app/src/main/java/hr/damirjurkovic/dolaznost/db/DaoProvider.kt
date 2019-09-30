package hr.damirjurkovic.dolaznost.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.damirjurkovic.dolaznost.Model.Course

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
                    "ClassDb"
                ).allowMainThreadQueries().build()
            }
            return instance as DaoProvider
        }
    }
}