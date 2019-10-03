package hr.damirjurkovic.attendance.ui.course.details.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.EXTRA_COURSE_ID
import hr.damirjurkovic.attendance.common.displayToast
import hr.damirjurkovic.attendance.common.subscribe
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.course.details.presentation.CourseDetailsViewModel
import kotlinx.android.synthetic.main.fragment_course_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseDetailsFragment : Fragment() {

    private val viewModel: CourseDetailsViewModel by viewModel()
    private val courseId by lazy { arguments?.getInt(EXTRA_COURSE_ID) ?: 0 }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_course_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setCourseID(courseId)
        initListeners()
        subscribeToData()
    }

    private fun initListeners() {
        btnAttendance.setOnClickListener { changeAttendance() }
    }

    private fun subscribeToData() {
        viewModel.courseLiveData.subscribe(this, this::handleCoursesChanged)
    }

    private fun handleCoursesChanged(course: Course) {
        tryDisplayDetails(course) //TODO zasto je u bazi courseDbId nullable
    }

    private fun changeAttendance() {
        val dialog = ChangeAttendanceDialogFragment.newInstance{hours, didAttend -> onChangedCourse(hours, didAttend)}
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onChangedCourse(hours: Int, didAttend: Boolean){
        viewModel.changeCourse(hours, didAttend)
    }

    private fun tryDisplayDetails(course: Course) {
        try {
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

    private fun checkIfCourseFinished(leftHoursAll: Double) {
        if (leftHoursAll <= 0) btnAttendance.isEnabled = false
    }

    companion object {
        fun newInstance(courseId: Int): CourseDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_COURSE_ID, courseId) }
            return CourseDetailsFragment()
                .apply { arguments = bundle }
        }
    }
}