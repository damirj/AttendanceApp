package hr.damirjurkovic.attendance

import android.app.Application
import android.content.Context
import hr.damirjurkovic.attendance.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AttendanceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this@AttendanceApp

        startKoin {
            androidContext(this@AttendanceApp)
            modules(listOf(repositoryModule, databaseModule, presentationModule, interactionModule, authenticationModule))
            androidLogger(Level.DEBUG)
        }
    }

    companion object {
        private lateinit var instance: AttendanceApp
        fun getAppContext(): Context = instance.applicationContext
    }

}