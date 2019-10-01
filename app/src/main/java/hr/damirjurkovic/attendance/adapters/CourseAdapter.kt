package hr.damirjurkovic.attendance.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.damirjurkovic.attendance.Model.Course
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.persistence.CourseRepository

class CourseAdapter(private val onItemSelected: (Course) -> Unit) : RecyclerView.Adapter<CourseHolder>() {

    private val repository = CourseRepository()
    private val data = repository.getAllCourses()

    //TODO izbaciti u viewmodel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.course_row, parent, false)
        return CourseHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.dataBinder(data[position], onItemSelected)
    }

    fun setData(data: MutableList<Course>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun removeTask(position: Int): Course{
        val course = data[position]
        data.removeAt(position)
        notifyItemRemoved(position)
        return course
    }
}