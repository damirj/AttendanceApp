package com.example.domain.interaction.course

import com.example.domain.model.Course
import com.example.domain.persistence.RepositoryInterface

class DeleteCourseUseCaseImpl(private val courseRepository: RepositoryInterface) :
    DeleteCourseUseCase {

    override operator fun invoke(course: Course) = courseRepository.deleteCourse(course)
}