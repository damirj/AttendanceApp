package hr.damirjurkovic.dolaznost.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.damirjurkovic.dolaznost.R


class StatisticsFragmnet : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics_framgnet, container, false)
    }


    companion object {
        fun newInstance(): Fragment{
            return StatisticsFragmnet()
        }
    }
}
