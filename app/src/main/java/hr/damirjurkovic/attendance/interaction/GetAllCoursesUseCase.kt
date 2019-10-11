package hr.damirjurkovic.attendance.interaction

import androidx.lifecycle.LiveData
import hr.damirjurkovic.attendance.model.Course

interface GetAllCoursesUseCase {

    operator fun invoke(): LiveData<MutableList<Course>>

}