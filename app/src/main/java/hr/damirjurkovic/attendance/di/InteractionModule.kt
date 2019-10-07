package hr.damirjurkovic.attendance.di

import hr.damirjurkovic.attendance.interaction.ChangeCourseUseCase
import hr.damirjurkovic.attendance.interaction.ChangeCourseUseCaseImpl
import hr.damirjurkovic.attendance.interaction.GetCourseUseCase
import hr.damirjurkovic.attendance.interaction.GetCourseUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {

    factory<GetCourseUseCase> { GetCourseUseCaseImpl(get()) }
    factory<ChangeCourseUseCase> {ChangeCourseUseCaseImpl(get())}

}