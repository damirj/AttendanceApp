package hr.damirjurkovic.attendance.ui.course.list.view.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.common.MyItemTouchHelper
import hr.damirjurkovic.attendance.common.showYesNoDialog
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.persistence.CourseRepository
import hr.damirjurkovic.attendance.ui.base.BaseFragment
import hr.damirjurkovic.attendance.ui.course.details.view.activities.startContainerActivity
import hr.damirjurkovic.attendance.ui.course.list.adapters.CourseAdapter
import kotlinx.android.synthetic.main.fragment_attendance.*

class AttendanceFragment : BaseFragment() {
//TODO viewmodel za attendanceFragment

    private val repository = CourseRepository()
    private val adapter by lazy {
        CourseAdapter {
            onItemSelected(
                it
            )
        }
    }

    override fun getLayoutRes() = R.layout.fragment_attendance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.course_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.courseMenu -> {
                repository.deleteAllCourses()
                refreshList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        setUpItemTouchHelper()
        pullToRefresh.setOnRefreshListener { onRefresh() }
    }

    private fun setUpItemTouchHelper() { //TODO korisiti safe call operator za context
        val deleteIcon = context?.let {
            ContextCompat.getDrawable(it, R.drawable.ic_delete_sweep_black_24dp)
        }

        deleteIcon?.let {
            val myItemTouchHelper = MyItemTouchHelper(
                { onSwiped(it) },
                it
            )
            val callback = myItemTouchHelper.setUpItemTouchHelper()
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(courseRecyclerView)
        }
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

    private fun addClass() {
        val dialog =
            AddCourseDialogFragment.newInstance {
                repository.insertCourse(it)
                refreshList()
            }
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onItemSelected(course: Course) {
        context?.run {
            startContainerActivity(
                this,
                course.courseDbId
            )
        }
    }

    private fun onSwiped(adapterPosition: Int) {
        context?.run {
            showYesNoDialog(
                positiveReply = { onYesClicked(adapterPosition) },
                negativeReply = { onNoClicked() })
        }
    }

    private fun onNoClicked() {
        refreshList()
    }

    private fun onYesClicked(position: Int) {
        repository.deleteCourse(adapter.removeTask(position))
        refreshList()
    }

    companion object {
        fun newInstance(): Fragment {
            return AttendanceFragment()
        }
    }
}
