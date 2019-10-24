package hr.damirjurkovic.attendance.di

import hr.damirjurkovic.attendance.interaction.*
import org.koin.dsl.module

val interactionModule = module {

    factory<GetCourseUseCase> { GetCourseUseCaseImpl(get()) }
    factory<ChangeCourseUseCase> { ChangeCourseUseCaseImpl(get()) }
    factory<DeleteAllCoursesUseCase> { DeleteAllCoursesUseCaseImpl(get()) }
    factory<DeleteCourseUseCase> { DeleteCourseUseCaseImpl(get()) }
    factory<GetAllCoursesUseCase> { GetAllCoursesUseCaseImpl(get()) }
    factory<InsertCourseUseCase> { InsertCourseUseCaseImpl(get()) }
    factory<SignOutUseCase> { SignOutUseCaseImpl(get()) }
    factory<CreateAccountUseCase> { CreateAccountUseCaseImpl(get()) }
    factory<ValidateFormUseCase> { ValidateFormUseCaseImpl(get()) }
    factory<SignInUseCase> { SignInUseCaseImpl(get()) }
    factory<CheckLoginUseCase> { CheckLoginUseCaseImpl(get()) }
}