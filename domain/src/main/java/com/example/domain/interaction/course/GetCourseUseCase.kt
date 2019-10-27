package com.example.domain.interaction.course

import androidx.lifecycle.LiveData
import com.example.domain.model.Course

interface GetCourseUseCase {

    operator fun invoke(courseId: Int): LiveData<Course>

}