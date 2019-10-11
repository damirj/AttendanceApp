package hr.damirjurkovic.attendance.di

import hr.damirjurkovic.attendance.persistence.CourseRepository
import hr.damirjurkovic.attendance.persistence.RepositoryInterface
import org.koin.dsl.module

val repositoryModule = module {
    factory<RepositoryInterface> { CourseRepository(get(), get()) }
}