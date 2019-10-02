package hr.damirjurkovic.attendance.ui.course.list.adapters

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import hr.damirjurkovic.attendance.model.Course
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

        when(data.alarmState){
            in 10.0..Double.MAX_VALUE -> cardId.setBackgroundColor(Color.parseColor(context.getString(R.string.goodCourseState)))
            in Double.MIN_VALUE..0.0 -> cardId.setBackgroundColor(Color.parseColor(context.getString(R.string.failCourseState)))
            else -> cardId.setBackgroundColor(Color.parseColor(context.getString(R.string.failCourseState)))
        }
    }
}