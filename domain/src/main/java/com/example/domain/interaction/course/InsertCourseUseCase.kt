package com.example.domain.interaction.course

import com.example.domain.model.Course

interface InsertCourseUseCase {

    operator fun invoke(course: Course)
}