package com.example.domain.interaction.course

import androidx.lifecycle.LiveData
import com.example.domain.model.Course

interface GetAllCoursesUseCase {

    operator fun invoke(): LiveData<MutableList<Course>>

}