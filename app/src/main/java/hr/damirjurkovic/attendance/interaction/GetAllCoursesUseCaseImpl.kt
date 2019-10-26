package hr.damirjurkovic.attendance.interaction

import androidx.lifecycle.LiveData
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface


class GetAllCoursesUseCaseImpl(private val courseRepository: RepositoryInterface) : GetAllCoursesUseCase{

    override operator fun invoke(): LiveData<MutableList<Course>> = courseRepository.getAllCourses()
}