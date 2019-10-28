package com.example.domain.interaction.course

import com.example.domain.model.Course

interface DeleteCourseUseCase {

    operator fun invoke(course: Course)
}