package hr.damirjurkovic.attendance.interaction

import androidx.lifecycle.LiveData
import hr.damirjurkovic.attendance.model.Course

interface GetCourseUseCase {

    operator fun invoke(courseId: Int): LiveData<Course>

}