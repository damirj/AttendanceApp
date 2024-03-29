package hr.damirjurkovic.attendance.ui.course.list.view.fragments

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.domain.model.Course
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.displayToast
import kotlinx.android.synthetic.main.fragment_dialog_new_course.*

class AddCourseDialogFragment(private val addCourse: (Course) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_new_course, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        saveCourseAction.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        if (isInputEmpty()) {
            context?.displayToast(getString(R.string.filedNotFilled))
            return
        } else {
            addCourse(createCourse())
            clearUi()
            dismiss()
        }
    }

    private fun createCourse(): Course {
        val lecture = newCourseLectureInput.text.toString().toInt()
        val exercise = newCourseExerciseInput.text.toString().toInt()
        val laboratory = newCourseLaboratoryInput.text.toString().toInt()
        val attendancePercent = newCoursePercentageInput.text.toString().toDouble()
        val attendanceNum: Double =
            (lecture + exercise + laboratory) * (attendancePercent / 100) - laboratory
        val leftHoursAll = (lecture + exercise).toDouble()

        return Course(
            courseName = newCourseTitleInput.text.toString(),
            numLectures = lecture,
            numExercises = exercise,
            numLaboratory = laboratory,
            attendancePercent = attendancePercent.toInt(),
            attendanceNum = attendanceNum,
            leftHoursQuota = attendanceNum,
            wentHours = 0.0,
            leftHoursAll = leftHoursAll,
            alarmState = leftHoursAll - attendanceNum
        )
    }

    private fun clearUi() {
        newCourseTitleInput.text.clear()
        newCourseLectureInput.text.clear()
        newCourseExerciseInput.text.clear()
        newCourseLaboratoryInput.text.clear()
        newCoursePercentageInput.text.clear()
    }

    private fun isInputEmpty(): Boolean {
        return isEmpty(newCourseTitleInput.text) ||
                isEmpty(newCourseLectureInput.text.toString()) ||
                isEmpty(newCourseExerciseInput.text.toString()) ||
                isEmpty(newCourseLaboratoryInput.text.toString())
    }

    companion object {
        fun newInstance(addCourse: (Course) -> Unit): AddCourseDialogFragment {
            return AddCourseDialogFragment(addCourse)
        }
    }
}