package hr.damirjurkovic.attendance.ui.course.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.model.Course


class CourseAdapter(private val onItemSelected: (Course) -> Unit) :
    RecyclerView.Adapter<CourseHolder>() {

    private val courses: MutableList<Course> = mutableListOf()

    fun setData(data: List<Course>) {
        courses.clear()
        courses.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.course_row, parent, false)
        return CourseHolder(v)
    }

    override fun getItemCount() = courses.size

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.dataBinder(courses[position], onItemSelected)
    }

    fun removeTask(position: Int): Course {
        val course = courses[position]
        courses.remove(course)
        notifyItemRemoved(position)
        return course
    }
}