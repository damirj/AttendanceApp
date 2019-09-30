package hr.damirjurkovic.dolaznost.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.common.EXTRA_COURSE_ID
import hr.damirjurkovic.dolaznost.common.showFragment
import hr.damirjurkovic.dolaznost.fragments.CourseDetailsFragment

class ContainerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        setUpUi()
    }

    private fun setUpUi() {
        val courseId = intent.getIntExtra(EXTRA_COURSE_ID, -1)
        if (courseId == -1){
            finish()
        }else{
            showFragment(R.id.fragmentContainer, CourseDetailsFragment.newInstance(courseId))
        }
    }
}