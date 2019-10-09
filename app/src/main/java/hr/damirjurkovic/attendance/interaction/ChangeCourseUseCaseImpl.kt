package hr.damirjurkovic.attendance.interaction

import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.RepositoryInterface

class ChangeCourseUseCaseImpl(private val courseRepository: RepositoryInterface) :
    ChangeCourseUseCase {

    override operator fun invoke(course: Course, hours: Int, didAttend: Boolean): Course {
        val hoursReal = if (hours > course.leftHoursAll) course.leftHoursAll.toInt() else hours
        if (didAttend) {
            val leftHoursQuota =
                if (course.leftHoursQuota - hoursReal > 0) course.leftHoursQuota - hoursReal else 0.0
            val wentHours = course.wentHours + hoursReal
            val leftHoursAll = course.leftHoursAll - hoursReal
            val alarmState = course.leftHoursAll - course.leftHoursQuota
            val course = course.copy(
                leftHoursQuota = leftHoursQuota,
                wentHours = wentHours,
                leftHoursAll = leftHoursAll,
                alarmState = alarmState
            )
            return courseRepository.updateCourse(course)
        } else {
            val leftHoursAll = course.leftHoursAll - hoursReal
            val alarmState = course.leftHoursAll - course.leftHoursQuota - hoursReal
            val course = course.copy(
                leftHoursQuota = course.leftHoursQuota,
                wentHours = course.wentHours,
                leftHoursAll = leftHoursAll,
                alarmState = alarmState
            )
            return courseRepository.updateCourse(course)
        }
    }

}