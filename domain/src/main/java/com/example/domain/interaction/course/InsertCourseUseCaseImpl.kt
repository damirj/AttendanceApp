package com.example.domain.interaction.course

import com.example.domain.model.Course
import com.example.domain.persistence.RepositoryInterface


class InsertCourseUseCaseImpl(private val courseRepository: RepositoryInterface) :
    InsertCourseUseCase {

    override operator fun invoke(course: Course) = courseRepository.insertCourse(course)

}