package hr.damirjurkovic.attendance.activity

import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.adapters.ViewPagerAdapter
import hr.damirjurkovic.attendance.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun setUpUi() {
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, this)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)?.setIcon(R.drawable.ic_access_time_black_24dp)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_timeline_black_24dp)
    }

}
