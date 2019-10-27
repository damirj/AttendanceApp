package hr.damirjurkovic.data.di

import com.google.firebase.auth.FirebaseAuth
import hr.damirjurkovic.data.common.EmailPasswordAuth
import org.koin.dsl.module

val authenticationModule = module {
    single { FirebaseAuth.getInstance() }
    factory { EmailPasswordAuth(get()) }
}