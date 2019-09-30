package hr.damirjurkovic.dolaznost.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import hr.damirjurkovic.dolaznost.Model.Course
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.common.EXTRA_COURSE_ID
import hr.damirjurkovic.dolaznost.common.displayToast
import hr.damirjurkovic.dolaznost.persistence.CourseRepository
import kotlinx.android.synthetic.main.fragment_course_details.*

class CourseDetailsFragment: Fragment(), ChangeAttendanceDialogFragment.CourseChangedListener {

    private var repository = CourseRepository()
    private var courseId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        val dialog = ChangeAttendanceDialogFragment.newInstance()
        dialog.setCourseChangeListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun tryDisplayDetails() {
        try {
            val course = repository.getCourse(courseId)
            Log.d("STASAD", course.alarmState.toString())
            displayCourse(course)
            checkIfCourseFinished(course.leftHoursAll)
        }catch (e: NoSuchElementException){
            context?.displayToast("No such course found")
        }
    }

    private fun displayCourse(course: Course) {
        courseName.text = course.courseName
        lectureHours.text = course.numLectures.toString()
        exerciseHours.text = course.numExercises.toString()
        laboratoryHours.text = course.numLaboratory.toString()
        stillMustComeHours.text = course.leftHoursQuota.toString()
        attendanceNumHours.text = (course.numLectures + course.numExercises).toString()
        attendanceBeenHours.text = course.wentHours.toString()
        courseLeftHours.text = course.leftHoursAll.toString()
    }

    override fun onCourseChanged(hours: Int, attendance: Boolean) {
        val course = repository.getCourse(courseId)
        var hoursReal = hours

        if (hours > course.leftHoursAll) hoursReal = course.leftHoursAll.toInt()

        when(attendance){
            true ->{
                repository.updateAttendanceState(
                    courseId,
                    course.leftHoursQuota - hoursReal,
                    course.wentHours + hoursReal,
                    course.leftHoursAll - hoursReal,
                    course.leftHoursAll - course.leftHoursQuota
                )
            }
            false ->{
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

    companion object{
        fun newInstance(courseId: Int): CourseDetailsFragment{
            val bundle = Bundle().apply { putInt(EXTRA_COURSE_ID, courseId) }
            return CourseDetailsFragment().apply { arguments = bundle }
        }
    }
}