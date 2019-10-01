package hr.damirjurkovic.attendance.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.damirjurkovic.attendance.Model.Course
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.EXTRA_COURSE_ID
import hr.damirjurkovic.attendance.common.displayToast
import hr.damirjurkovic.attendance.persistence.CourseRepository
import kotlinx.android.synthetic.main.fragment_course_details.*

class CourseDetailsFragment : Fragment() {

    private var repository = CourseRepository()
    private var courseId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        arguments?.getInt(EXTRA_COURSE_ID)?.let { courseId = it }
        tryDisplayDetails()
    }

    private fun checkIfCourseFinished(leftHoursAll: Double) {
        if (leftHoursAll <= 0) btnAttendance.isEnabled = false
    }

    private fun initListeners() {

        btnAttendance.setOnClickListener { changeAttendance() }
    }

    private fun changeAttendance() {
        val dialog = ChangeAttendanceDialogFragment.newInstance { hours, attendance ->
            onCourseChanged(
                hours,
                attendance
            )
        }
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun tryDisplayDetails() {
        try {
            val course = repository.getCourse(courseId)
            displayCourse(course)
            checkIfCourseFinished(course.leftHoursAll)
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noCourse))
        }
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

    private fun onCourseChanged(hours: Int, attendance: Boolean) {
        val course = repository.getCourse(courseId)
        var hoursReal = hours

        if (hours > course.leftHoursAll) hoursReal = course.leftHoursAll.toInt()

        when (attendance) {
            true -> {
                repository.updateAttendanceState(
                    courseId,
                    if (course.leftHoursQuota - hoursReal > 0) course.leftHoursQuota - hoursReal else 0.0,
                    course.wentHours + hoursReal,
                    course.leftHoursAll - hoursReal,
                    course.leftHoursAll - course.leftHoursQuota
                )
            }
            false -> {
                repository.updateAttendanceState(
                    courseId,
                    course.leftHoursQuota,
                    course.wentHours,
                    course.leftHoursAll - hoursReal,
                    course.leftHoursAll - hoursReal - course.leftHoursQuota
                )
            }
        }
        tryDisplayDetails()
    }

    companion object {
        fun newInstance(courseId: Int): CourseDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_COURSE_ID, courseId) }
            return CourseDetailsFragment().apply { arguments = bundle }
        }
    }
}