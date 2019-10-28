package com.example.domain.persistence


import androidx.lifecycle.LiveData
import com.example.domain.model.Course

interface RepositoryInterface {

    fun getAllCourses(): LiveData<MutableList<Course>>

    fun insertCourse(course: Course)

    fun updateCourse(course: Course)

    fun deleteCourse(course: Course)

    fun deleteAllCourses()

    fun getCourse(courseId: Int): LiveData<Course>
}