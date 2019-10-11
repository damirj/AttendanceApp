package hr.damirjurkovic.attendance.ui.course.list.presentation

import hr.damirjurkovic.attendance.interaction.DeleteAllCoursesUseCase
import hr.damirjurkovic.attendance.interaction.DeleteCourseUseCase
import hr.damirjurkovic.attendance.interaction.GetAllCoursesUseCase
import hr.damirjurkovic.attendance.interaction.InsertCourseUseCase
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseViewModel
import hr.damirjurkovic.attendance.ui.base.Success
import hr.damirjurkovic.attendance.ui.course.list.view.AllCoursesDeleted
import hr.damirjurkovic.attendance.ui.course.list.view.CourseAdded
import hr.damirjurkovic.attendance.ui.course.list.view.CourseDeleted
import hr.damirjurkovic.attendance.ui.course.list.view.CourseListEffect


class CourseListViewModel(
    private val getAllCourses: GetAllCoursesUseCase,
    private val insertCourse: InsertCourseUseCase,
    private val deleteCourse: DeleteCourseUseCase,
    private val removeAllCourses: DeleteAllCoursesUseCase
) :
    BaseViewModel<List<Course>, CourseListEffect>() {

    init {
        loadCourses()
    }

    fun addCourse(course: Course) {
        _viewEffects.value = CourseAdded(insertCourse(course))
    }

    fun deleteAllCourses() {
        removeAllCourses()
        _viewEffects.value = AllCoursesDeleted
    }


    fun deleteCourse(course: Course, position: Int) {
        deleteCourse(course)
        _viewEffects.value = CourseDeleted(position)
    }

    private fun loadCourses() {
        _viewState.value = Success(getAllCourses())
    }

    fun refresh() {
        loadCourses()
    }
}