package hr.damirjurkovic.attendance.ui.course.list.view

import hr.damirjurkovic.attendance.model.Course

sealed class CourseListEffect
class CourseAdded(val course: Course) : CourseListEffect()
class CourseDeleted(val position: Int) : CourseListEffect()
object AllCoursesDeleted: CourseListEffect()
