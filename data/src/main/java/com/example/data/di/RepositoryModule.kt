package com.example.data.di

import com.example.data.persistence.CourseRepository
import com.example.domain.persistence.RepositoryInterface
import org.koin.dsl.module

val repositoryModule = module {
    factory<RepositoryInterface> { CourseRepository(get(), get()) }
}