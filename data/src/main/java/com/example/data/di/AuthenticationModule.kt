package com.example.data.di

import com.example.data.persistence.EmailPasswordAuth
import com.example.domain.persistence.EmailPasswordAuthInterface
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val authenticationModule = module {
    single { FirebaseAuth.getInstance() }
    single<EmailPasswordAuthInterface> { EmailPasswordAuth(get()) }
}