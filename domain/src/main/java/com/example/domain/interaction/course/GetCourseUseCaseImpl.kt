package com.example.domain.interaction.course

import com.example.domain.persistence.RepositoryInterface

class GetCourseUseCaseImpl(private val courseRepository: RepositoryInterface) :
    GetCourseUseCase {

    override operator fun invoke(courseId: Int) = courseRepository.getCourse(courseId)

}