package hr.damirjurkovic.attendance.ui.course.list.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
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
import hr.damirjurkovic.attendance.ui.course.list.view.CourseListEffect
import hr.damirjurkovic.attendance.ui.course.list.view.SignedOut
import hr.damirjurkovic.attendance.ui.login.activities.EmailPasswordActivity
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
        viewModel.coursesLiveData.subscribe(this, this::handleCoursesChanges)
        viewModel.viewEffects.subscribe(this, this::handleViewEffects)
    }

    private fun handleCoursesChanges(courses: MutableList<Course>) {
        refreshList(courses)
    }

    private fun handleViewEffects(viewEffect: CourseListEffect) {
        when (viewEffect) {
            is SignedOut -> backToLoginPage()
        }
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
            R.id.signOut -> {
                viewModel.signOutFromAcc()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpItemTouchHelper() {
        val myItemTouchHelper = MyItemTouchHelper { position -> onSwiped(position) }
        myItemTouchHelper.getItemTouchHelper().attachToRecyclerView(courseRecyclerView)
    }


    private fun onRefresh() {
        viewModel.coursesLiveData.value?.let { adapter.setData(it) }
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
        viewModel.removeCourse(adapter.getCourse(position))
    }

    private fun backToLoginPage() {
        val intent = Intent(activity, EmailPasswordActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance(): Fragment {
            return AttendanceFragment()
        }
    }
}
