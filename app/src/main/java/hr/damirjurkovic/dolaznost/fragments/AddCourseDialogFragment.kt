package hr.damirjurkovic.dolaznost.fragments

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import hr.damirjurkovic.dolaznost.Model.Course
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.common.displayToast
import hr.damirjurkovic.dolaznost.persistence.CourseRepository
import kotlinx.android.synthetic.main.fragment_dialog_new_course.*

class AddCourseDialogFragment: DialogFragment() {

    private var courseCreatedListener: CourseCreatedListener? = null
    private var repository = CourseRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_new_course, container)
    }

    interface CourseCreatedListener{
        fun onCourseCreated()
    }

    fun setCourseCreatedListener(listener: CourseCreatedListener){
        courseCreatedListener = listener
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners(){
        saveCourseAction.setOnClickListener{ saveTask() }
    }

    private fun saveTask() {
        if(isInputEmpty()){
            context?.displayToast("Nisi popunio sva polja")
            return
        }else {
            val lecture = newCourseLectureInput.text.toString().toInt()
            val exercise = newCourseExerciseInput.text.toString().toInt()
            val laboratory = newCourseLaboratoryInput.text.toString().toInt()
            val attendancePercent = newCoursePercentageInput.text.toString().toDouble()
            var attendanceNum: Double = (lecture + exercise + laboratory) * (attendancePercent/100)
            var leftHoursQuota = attendanceNum - laboratory
            var leftHoursAll = (lecture + exercise).toDouble()

            var course = Course(
                courseName = newCourseTitleInput.text.toString(),
                numLectures = lecture,
                numExercises = exercise,
                numLaboratory = laboratory,
                attendancePercent = attendancePercent.toInt(),
                attendanceNum = attendanceNum,
                leftHoursQuota = leftHoursQuota,
                wentHours = 0.0,
                leftHoursAll = leftHoursAll,
                alarmState = leftHoursAll - leftHoursQuota
            )
            repository.insertCourse(course)

            clearUi()
            courseCreatedListener?.onCourseCreated()
            dismiss()
        }
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

    companion object{
        fun newInstance(): AddCourseDialogFragment {
            return AddCourseDialogFragment()
        }
    }
}