package com.example.domain.interaction.course

import com.example.domain.model.Course

interface ChangeCourseUseCase {

     operator fun invoke(course: Course, hours: Int, didAttend: Boolean)

}