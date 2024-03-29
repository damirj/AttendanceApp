package hr.damirjurkovic.attendance.ui.course.details.view.fragments

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.displayToast
import hr.damirjurkovic.attendance.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.fragment_dialog_change_course.*


class ChangeAttendanceDialogFragment(private val changeCourse: (Int, Boolean) -> Unit) :
    BaseDialogFragment() {

    override fun getLayoutRes() = R.layout.fragment_dialog_change_course
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
        btnChangeCourse.setOnClickListener { saveChanges() }
    }

    private fun saveChanges() {
        if (isInputFilled()) {
            changeCourse(numOfHours.text.toString().toInt(), btnAttend.isChecked)
            clearUi()
            dismiss()
        } else {
            context?.displayToast(getString(R.string.fieldMustFill))
            return
        }
    }

    private fun clearUi() {
        btnGroup.clearCheck()
        numOfHours.text.clear()
    }

    private fun isInputFilled(): Boolean =
        (btnAttend.isChecked || btnMissed.isChecked) && numOfHours.text.isNotEmpty()

    companion object {
        fun newInstance(changeCourse: (hours: Int, didAttend: Boolean) -> Unit): ChangeAttendanceDialogFragment {
            return ChangeAttendanceDialogFragment(changeCourse)
        }
    }
}