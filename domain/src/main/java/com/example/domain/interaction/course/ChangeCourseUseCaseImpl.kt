package com.example.domain.interaction.course

import com.example.domain.common.ALARM_STATE_1
import com.example.domain.model.Course
import com.example.domain.persistence.RepositoryInterface

class ChangeCourseUseCaseImpl(private val courseRepository: RepositoryInterface) :
    ChangeCourseUseCase {

    override operator fun invoke(course: Course, hours: Int, didAttend: Boolean) {
        val hoursReal = if (hours > course.leftHoursAll) course.leftHoursAll.toInt() else hours
        if (didAttend) {
            val leftHoursQuota =
                if (course.leftHoursQuota - hoursReal > 0) course.leftHoursQuota - hoursReal else 0.0
            val wentHours = course.wentHours + hoursReal
            val leftHoursAll = course.leftHoursAll - hoursReal
            val alarmState = if (course.leftHoursQuota == 0.0) {
                ALARM_STATE_1
            } else {
                course.leftHoursAll - course.leftHoursQuota
            }
            val courseUpdate = course.copy(
                leftHoursQuota = leftHoursQuota,
                wentHours = wentHours,
                leftHoursAll = leftHoursAll,
                alarmState = alarmState
            )
             courseRepository.updateCourse(courseUpdate)
        } else {
            val leftHoursAll = course.leftHoursAll - hoursReal
            val alarmState = if (course.leftHoursQuota == 0.0) {
                ALARM_STATE_1
            } else {
                course.leftHoursAll - course.leftHoursQuota - hoursReal
            }
            val courseUpdate = course.copy(
                leftHoursQuota = course.leftHoursQuota,
                wentHours = course.wentHours,
                leftHoursAll = leftHoursAll,
                alarmState = alarmState
            )
             courseRepository.updateCourse(courseUpdate)
        }
    }

}
