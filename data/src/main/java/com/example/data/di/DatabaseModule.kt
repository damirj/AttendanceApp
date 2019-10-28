package com.example.data.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { FirebaseDatabase.getInstance().apply { this.setPersistenceEnabled(true) } }

}