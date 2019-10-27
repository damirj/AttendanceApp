package hr.damirjurkovic.attendance

import android.app.Application
import com.example.domain.di.interactionModule
import hr.damirjurkovic.attendance.di.presentationModule
import hr.damirjurkovic.data.di.authenticationModule
import hr.damirjurkovic.data.di.databaseModule
import hr.damirjurkovic.data.di.repositoryModule
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
            modules(
                listOf(
                    repositoryModule,
                    databaseModule,
                    presentationModule,
                    interactionModule,
                    authenticationModule
                )
            )
            androidLogger(Level.DEBUG)
        }
    }

    companion object {
        lateinit var instance: AttendanceApp
            private set
    }

}