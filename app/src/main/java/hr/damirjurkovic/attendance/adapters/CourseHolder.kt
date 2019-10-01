package hr.damirjurkovic.attendance.adapters

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.damirjurkovic.attendance.Model.Course
import hr.damirjurkovic.attendance.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.course_row.view.*


class CourseHolder(override var containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    fun dataBinder(data: Course, onItemSelected: (Course) -> Unit) = with(containerView) {
        setOnClickListener { onItemSelected(data) }
        courseName.text = data.courseName
        leftHoursQuotaState.text = data.leftHoursQuota.toString()
        leftHoursAllState.text = data.leftHoursAll.toString()

        if (data.alarmState > 10) {
            cardId.setBackgroundColor(Color.parseColor(context.getString(R.string.goodCourseState)))
        } else if (data.alarmState > 0 && data.alarmState < 10) {
            cardId.setBackgroundColor(Color.parseColor(context.getString(R.string.alarmCourseState)))
        } else if (data.alarmState < 0) {
            cardId.setBackgroundColor(Color.parseColor(context.getString(R.string.failCourseState)))
        }
    }
}