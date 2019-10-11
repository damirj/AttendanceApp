package hr.damirjurkovic.attendance.ui.course.details.view.fragments

import android.os.Bundle
import android.view.View
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.EXTRA_COURSE_ID
import hr.damirjurkovic.attendance.common.subscribe
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseFragment
import hr.damirjurkovic.attendance.ui.course.details.presentation.CourseDetailsViewModel
import hr.damirjurkovic.attendance.ui.course.details.view.CourseDetailsEffect
import hr.damirjurkovic.attendance.ui.course.details.view.DisableAttendanceBtn
import kotlinx.android.synthetic.main.fragment_course_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseDetailsFragment : BaseFragment() {
    override fun getLayoutRes() = R.layout.fragment_course_details

    private val viewModel: CourseDetailsViewModel by viewModel()
    private val courseId by lazy { arguments?.getInt(EXTRA_COURSE_ID) ?: 0 }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCourse(courseId)
        initListeners()
        subscribeToData()
    }

    private fun initListeners() {
        btnAttendance.setOnClickListener { changeAttendance() }
    }

    private fun subscribeToData() {
        viewModel.course.subscribe(this, this::handleCourseChanged)
        viewModel.viewEffects.subscribe(this, this::handleCourseChangedEffect)
    }

    private fun handleCourseChanged(course: Course) {
        displayCourse(course)
    }

    private fun handleCourseChangedEffect(viewEffect: CourseDetailsEffect) {
        when (viewEffect) {
            is DisableAttendanceBtn -> btnAttendance.isEnabled = false
        }
    }

    private fun changeAttendance() {
        val dialog = ChangeAttendanceDialogFragment.newInstance { hours, didAttend ->
            onChangedCourse(
                hours,
                didAttend
            )
        }
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onChangedCourse(hours: Int, didAttend: Boolean) {
        viewModel.changeCourse(hours, didAttend)
    }

    private fun displayCourse(course: Course) {
        courseName.text = course.courseName
        lectureHours.text = course.numLectures.toString()
        exerciseHours.text = course.numExercises.toString()
        laboratoryHours.text = course.numLaboratory.toString()
        stillMustComeHours.text = course.leftHoursQuota.toString()
        attendanceNumHours.text = course.attendanceNum.toString()
        attendanceBeenHours.text = course.wentHours.toString()
        courseLeftHours.text = course.leftHoursAll.toString()
    }

    companion object {
        fun newInstance(courseId: Int): CourseDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_COURSE_ID, courseId) }
            return CourseDetailsFragment()
                .apply { arguments = bundle }
        }
    }
}