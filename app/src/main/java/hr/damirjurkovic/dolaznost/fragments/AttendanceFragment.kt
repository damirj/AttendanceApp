package hr.damirjurkovic.dolaznost.fragments

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import hr.damirjurkovic.dolaznost.Model.Course
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.activity.ContainerActivity
import hr.damirjurkovic.dolaznost.adapters.CourseAdapter
import hr.damirjurkovic.dolaznost.common.EXTRA_COURSE_ID
import hr.damirjurkovic.dolaznost.persistence.CourseRepository
import kotlinx.android.synthetic.main.fragment_attendance.*

class AttendanceFragment : Fragment(), AddCourseDialogFragment.CourseCreatedListener, DeleteAlertDialog.DeleteDialogListener {

    private val repository = CourseRepository()
    private val adapter by lazy { CourseAdapter { onItemSelected(it) } }
    private val swipeBackground: ColorDrawable = ColorDrawable(Color.RED)
    private lateinit var deleteIcon: Drawable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_attendance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        refreshList()
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun initUi() {
        courseRecyclerView.layoutManager = LinearLayoutManager(context)
        courseRecyclerView.adapter = adapter

        deleteIcon = ContextCompat.getDrawable(this.context!!, R.drawable.ic_delete_sweep_black_24dp)!!

        val itemTouchHelperCallback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dialog = DeleteAlertDialog()
                dialog.setDeleteDialogListener(this@AttendanceFragment)
                dialog.setDeleteTask(viewHolder)
                dialog.show(childFragmentManager, dialog.tag)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                val itemView = viewHolder.itemView

                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight) / 2
                swipeBackground.setBounds(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                deleteIcon.setBounds(itemView.right - iconMargin - deleteIcon.intrinsicWidth, itemView.top + iconMargin, itemView.right - iconMargin,
                    itemView.bottom - iconMargin)
                swipeBackground.draw(c)
                c.save()
                c.clipRect(itemView.right + dX.toInt(), itemView.top, itemView.right, itemView.bottom)
                deleteIcon.draw(c)
                c.restore()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(courseRecyclerView)

        pullToRefresh.setOnRefreshListener { onRefresh() }
    }

    private fun onRefresh() {
        refreshList()
        pullToRefresh.isRefreshing = false
    }

    private fun initListeners() {
        addClass.setOnClickListener { addClass() }
    }

    private fun refreshList() {
        adapter.setData(repository.getAllCourses())
    }

    private fun addClass(){
        val dialog = AddCourseDialogFragment.newInstance()
        dialog.setCourseCreatedListener(this)
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onItemSelected(course: Course) {
        val  detailsIntent = Intent(context, ContainerActivity::class.java).apply {
            putExtra(EXTRA_COURSE_ID, course.courseDbId)
        }
        startActivity(detailsIntent)
    }

    override fun onCourseCreated() {
        refreshList()
    }

    override fun onNoClicked() {
        refreshList()
    }

    override fun onYesClicked(viewHolder: RecyclerView.ViewHolder?) {
        deleteCourse(viewHolder!!)
    }

    private fun deleteCourse(viewHolder: RecyclerView.ViewHolder) {
        repository.deleteCourse(adapter.removeTask(viewHolder))
        refreshList()
    }

    companion object {
        fun newInstance(): Fragment{
            return AttendanceFragment()
        }
    }
}
