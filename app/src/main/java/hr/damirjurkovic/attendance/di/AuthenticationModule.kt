package hr.damirjurkovic.attendance.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val authenticationModule = module {
    single { FirebaseAuth.getInstance() }
}