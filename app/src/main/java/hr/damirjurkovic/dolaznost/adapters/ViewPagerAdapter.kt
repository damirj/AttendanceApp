package hr.damirjurkovic.dolaznost.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.damirjurkovic.dolaznost.fragments.AttendanceFragment
import hr.damirjurkovic.dolaznost.fragments.StatisticsFragmnet

class ViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AttendanceFragment.newInstance()
            else -> StatisticsFragmnet.newInstance()
        }
    }

    override fun getCount(): Int = 2
}