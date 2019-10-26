package hr.damirjurkovic.attendance.di

import hr.damirjurkovic.attendance.ui.course.details.presentation.CourseDetailsViewModel
import hr.damirjurkovic.attendance.ui.course.list.presentation.CourseListViewModel
import hr.damirjurkovic.attendance.ui.login.presentation.EmailPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CourseListViewModel(get(), get(), get(), get(), get()) }
    viewModel {
        CourseDetailsViewModel(get(), get())
    }
    viewModel { EmailPasswordViewModel(get(), get(), get(), get()) }
}