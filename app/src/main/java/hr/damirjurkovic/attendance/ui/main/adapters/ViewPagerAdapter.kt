package hr.damirjurkovic.attendance.ui.main.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.ui.course.list.view.fragments.AttendanceFragment
import hr.damirjurkovic.attendance.ui.statistics.view.fragments.StatisticsFragmnet

class ViewPagerAdapter(fm: FragmentManager, val context: Context) : FragmentPagerAdapter(fm) {

    private val fragmentList = listOf(AttendanceFragment.newInstance(), StatisticsFragmnet.newInstance())

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
          0 -> context.resources.getString(R.string.attendance)
          else -> context.resources.getString(R.string.statistics)
        }
    }

    override fun getCount(): Int = 2
}

