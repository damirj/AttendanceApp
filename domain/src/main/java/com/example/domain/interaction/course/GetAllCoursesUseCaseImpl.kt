package com.example.domain.interaction.course

import androidx.lifecycle.LiveData
import com.example.domain.model.Course
import com.example.domain.persistence.RepositoryInterface


class GetAllCoursesUseCaseImpl(private val courseRepository: RepositoryInterface) :
    GetAllCoursesUseCase {

    override operator fun invoke(): LiveData<MutableList<Course>> = courseRepository.getAllCourses()
}