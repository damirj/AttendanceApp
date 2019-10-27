package hr.damirjurkovic.data.di

import com.example.domain.persistence.RepositoryInterface
import hr.damirjurkovic.data.persistence.CourseRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<RepositoryInterface> { CourseRepository(get(), get()) }
}