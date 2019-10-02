package hr.damirjurkovic.attendance.ui.statistics.view.fragments

import androidx.fragment.app.Fragment
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.ui.base.BaseFragment


class StatisticsFragmnet : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_statistics_fragmnet

    companion object {
        fun newInstance(): Fragment {
            return StatisticsFragmnet()
        }
    }
}
