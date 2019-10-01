package hr.damirjurkovic.attendance

import android.app.Application
import android.content.Context

class AttendanceApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this@AttendanceApp
    }

    companion object{
        private lateinit var instance: AttendanceApp
        fun getAppContext(): Context = instance.applicationContext
    }

}