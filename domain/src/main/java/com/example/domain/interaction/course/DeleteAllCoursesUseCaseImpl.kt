package com.example.domain.interaction.course

import com.example.domain.persistence.RepositoryInterface

class DeleteAllCoursesUseCaseImpl(private val courseRepository: RepositoryInterface) :
    DeleteAllCoursesUseCase {

    override fun invoke() = courseRepository.deleteAllCourses()

}