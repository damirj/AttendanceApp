package hr.damirjurkovic.dolaznost.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.damirjurkovic.dolaznost.Model.Course
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.persistence.CourseRepository

class CourseAdapter(private val onItemSelected: (Course) -> Unit) : RecyclerView.Adapter<CourseHolder>() {

    private val repository = CourseRepository()
    private val data = repository.getAllCourses()

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

    fun removeTask(viewHolder: RecyclerView.ViewHolder): Course{
        var course = data[viewHolder.adapterPosition]
        data.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)
        return course
    }
}