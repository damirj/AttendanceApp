package hr.damirjurkovic.attendance.di

import androidx.room.Room
import hr.damirjurkovic.attendance.common.DATABASE_NAME
import hr.damirjurkovic.attendance.db.DaoProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), DaoProvider::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()
    }
    single { get<DaoProvider>().courseDao() }
}