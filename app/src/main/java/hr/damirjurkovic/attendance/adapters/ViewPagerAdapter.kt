package hr.damirjurkovic.attendance.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.fragments.AttendanceFragment
import hr.damirjurkovic.attendance.fragments.StatisticsFragmnet

class ViewPagerAdapter(fm: FragmentManager, val context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AttendanceFragment.newInstance()
            else -> StatisticsFragmnet.newInstance()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
          0 -> context.resources.getString(R.string.attendance)
          else -> context.resources.getString(R.string.statistics)
        }
    }

    override fun getCount(): Int = 2
}

