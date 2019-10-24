package hr.damirjurkovic.attendance.di

import com.google.firebase.auth.FirebaseAuth
import hr.damirjurkovic.attendance.common.EmailPasswordAuth
import org.koin.dsl.module

val authenticationModule = module {
    single { FirebaseAuth.getInstance() }
    factory { EmailPasswordAuth(get()) }
}