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
import hr.damirjurkovic.attendance.common.subscribe
import hr.damirjurkovic.attendance.model.Course
import hr.damirjurkovic.attendance.ui.base.BaseFragment
import hr.damirjurkovic.attendance.ui.course.details.view.activities.startContainerActivity
import hr.damirjurkovic.attendance.ui.course.list.adapters.CourseAdapter
import hr.damirjurkovic.attendance.ui.course.list.presentation.CourseListViewModel
import kotlinx.android.synthetic.main.fragment_attendance.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AttendanceFragment : BaseFragment() {

    private val viewModel: CourseListViewModel by viewModel()
    private val adapter by lazy { CourseAdapter { onItemSelected(it) } }

    override fun getLayoutRes() = R.layout.fragment_attendance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
        subscribeToData()
    }

    private fun initUi() {
        courseRecyclerView.layoutManager = LinearLayoutManager(context)
        courseRecyclerView.adapter = adapter
        setUpItemTouchHelper()
        pullToRefresh.setOnRefreshListener { onRefresh() }
    }

    private fun initListeners() {
        addClass.setOnClickListener { showCreateClassDialog() }
    }

    private fun subscribeToData() {
        viewModel.coursesLiveData.subscribe(this, this::handleCoursesChanged)
    }

    private fun handleCoursesChanged(courses: MutableList<Course>) {
        refreshList(courses)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.course_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.courseMenu -> {
                viewModel.deleteAllCourses()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpItemTouchHelper() {
        val deleteIcon = context?.let {
            ContextCompat.getDrawable(it, R.drawable.ic_delete_sweep_black_24dp)
        }

        deleteIcon?.let {
            val myItemTouchHelper = MyItemTouchHelper(
                { position -> onSwiped(position) },
                it
            )
            val callback = myItemTouchHelper.setUpItemTouchHelper()
            val itemTouchHelper = ItemTouchHelper(callback)
            itemTouchHelper.attachToRecyclerView(courseRecyclerView)
        }
    }

    private fun onRefresh() {
        viewModel.refresh()
        pullToRefresh.isRefreshing = false
    }

    private fun refreshList(courses: List<Course>) {
        adapter.setData(courses)
    }

    private fun showCreateClassDialog() {
        val dialog = AddCourseDialogFragment.newInstance { onAddCourse(it) }
        dialog.show(childFragmentManager, dialog.tag)
    }

    private fun onAddCourse(course: Course) {
        viewModel.addCourse(course)
        //TODO ispraviti bug: kad se napravi novi course ne mogu ga odma otvoriti vec moram refreshati
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
                negativeReply = { onNoClicked(adapterPosition) })
        }
    }

    private fun onNoClicked(adapterPosition: Int) {
        adapter.notifyItemChanged(adapterPosition)
    }

    private fun onYesClicked(position: Int) {
        viewModel.deleteCourse(adapter.removeTask(position))
    }

    companion object {
        fun newInstance(): Fragment {
            return AttendanceFragment()
        }
    }
}
