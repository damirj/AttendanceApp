package hr.damirjurkovic.attendance.ui.course.details.view.activities

import android.content.Context
import android.content.Intent
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.ui.base.BaseActivity
import hr.damirjurkovic.attendance.common.EXTRA_COURSE_ID
import hr.damirjurkovic.attendance.common.showFragment
import hr.damirjurkovic.attendance.ui.course.details.view.fragments.CourseDetailsFragment

fun startContainerActivity(from: Context, courseId: Int?) =
    from.startActivity(Intent(from, ContainerActivity::class.java).apply {
        putExtra(
            EXTRA_COURSE_ID,
            courseId
        )
    })

class ContainerActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_container

    override fun setUpUi() {
        val courseId = intent.getIntExtra(EXTRA_COURSE_ID, -1)
        if (courseId == -1) {
            finish()
        } else {
            showFragment(R.id.fragmentContainer, CourseDetailsFragment.newInstance(courseId))
        }
    }
}
