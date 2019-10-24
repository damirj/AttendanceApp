package hr.damirjurkovic.attendance.ui.main.activities

import android.content.Context
import android.content.Intent
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.ui.base.BaseActivity
import hr.damirjurkovic.attendance.ui.main.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

fun startMainActivity(from: Context) =
    from.startActivity(Intent(from, MainActivity::class.java))

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun setUpUi() {
        viewPager.adapter =
            ViewPagerAdapter(
                supportFragmentManager,
                this
            )
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)?.setIcon(R.drawable.ic_access_time_black_24dp)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_timeline_black_24dp)
    }

}
