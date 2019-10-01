package hr.damirjurkovic.attendance.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import hr.damirjurkovic.attendance.Model.Course
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.activity.startContainerActivity
import hr.damirjurkovic.attendance.adapters.CourseAdapter
import hr.damirjurkovic.attendance.base.BaseFragment
import hr.damirjurkovic.attendance.common.MyItemTouchHelper
import hr.damirjurkovic.attendance.persistence.CourseRepository
import kotlinx.android.synthetic.main.fragment_attendance.*

class AttendanceFragment : BaseFragment() {
//TODO viewmodel za attendanceFragment

    private val repository = CourseRepository()
    private val adapter by lazy { CourseAdapter { onItemSelected(it) } }

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


    private fun setUpItemTouchHelper() {
        val deleteIcon =
            ContextCompat.getDrawable(this.context!!, R.drawable.ic_delete_sweep_black_24dp)!!
        val myItemTouchHelper = MyItemTouchHelper(
            { onYesClicked(it) },
            { onNoClicked() },
            deleteIcon,
            context!!
        ) //TODO jel moze ovo drugacije tj. bez '!!'
        val callback = myItemTouchHelper.setUpItemTouchHelper()
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(courseRecyclerView)
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
        val dialog = AddCourseDialogFragment.newInstance {
            repository.insertCourse(it)
            refreshList()
        }
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onItemSelected(course: Course) {
        context?.run { startContainerActivity(this, course.courseDbId) }
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
