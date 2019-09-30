package hr.damirjurkovic.dolaznost.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.common.displayToast
import kotlinx.android.synthetic.main.fragment_dialog_change_course.*

class ChangeAttendanceDialogFragment: DialogFragment() {

    private var courseChangedListener: CourseChangedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dialog_change_course, container)
    }

    interface CourseChangedListener{
        fun onCourseChanged(hours: Int, attendance: Boolean)
    }

    fun setCourseChangeListener(listener: CourseChangedListener){
        courseChangedListener = listener
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        btnChangeCourse.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        if(isInputFilled()){
            if (btnAttend.isChecked){
                courseChangedListener?.onCourseChanged(numOfHours.text.toString().toInt(), true)
            }else{
                courseChangedListener?.onCourseChanged(numOfHours.text.toString().toInt(), false)
            }
            clearUi()
            dismiss()
        }else{
            context?.displayToast("You have to fill the inputs!")
            return
        }
    }

    private fun clearUi() {
        btnGroup.clearCheck()
        numOfHours.text.clear()
    }

    private fun isInputFilled(): Boolean = (btnAttend.isChecked || btnMissed.isChecked) && numOfHours.text.isNotEmpty()

    companion object{
        fun newInstance(): ChangeAttendanceDialogFragment{
            return ChangeAttendanceDialogFragment()
        }
    }

}