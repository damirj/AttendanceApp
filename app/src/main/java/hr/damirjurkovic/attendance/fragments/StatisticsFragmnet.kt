package hr.damirjurkovic.attendance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.damirjurkovic.attendance.R
import hr.damirjurkovic.attendance.base.BaseFragment


class StatisticsFragmnet : BaseFragment() {

    override fun getLayoutRes() = R.layout.fragment_statistics_fragmnet

    companion object {
        fun newInstance(): Fragment {
            return StatisticsFragmnet()
        }
    }
}
