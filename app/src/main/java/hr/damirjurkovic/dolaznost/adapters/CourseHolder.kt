package hr.damirjurkovic.dolaznost.adapters

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import hr.damirjurkovic.dolaznost.AttendanceApp
import hr.damirjurkovic.dolaznost.Model.Course
import hr.damirjurkovic.dolaznost.R
import kotlinx.android.synthetic.main.course_row.view.*


class CourseHolder(var containerView: View) : RecyclerView.ViewHolder(containerView){

    fun dataBinder(data: Course, onItemSelected: (Course) -> Unit){
        containerView.setOnClickListener { onItemSelected(data) }
        containerView.courseName.text = data.courseName
        containerView.leftHoursQuotaState.text = data.leftHoursQuota.toString()
        containerView.leftHoursAllState.text = data.leftHoursAll.toString()

        if (data.alarmState > 10){
            containerView.cardId.setBackgroundColor(Color.parseColor("#167306"))
        }else if (data.alarmState > 0 && data.alarmState < 10){
            containerView.cardId.setBackgroundColor(Color.parseColor("#E6DB15"))
        }else if (data.alarmState < 0){
            containerView.cardId.setBackgroundColor(Color.parseColor("#A61C07"))
        }
    }
}