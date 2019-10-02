package hr.damirjurkovic.attendance.di

import hr.damirjurkovic.attendance.ui.course.list.presentation.CourseAdapterViewModel
import hr.damirjurkovic.attendance.ui.course.list.presentation.CourseListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CourseListViewModel(get()) }
    viewModel { CourseAdapterViewModel(get()) }
}