package hr.damirjurkovic.dolaznost.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.damirjurkovic.dolaznost.R
import hr.damirjurkovic.dolaznost.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpUi()
    }

    private fun setUpUi() {
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)?.setIcon(R.drawable.ic_access_time_black_24dp)
        tabs.getTabAt(0)?.text = "Dolaznost"
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_timeline_black_24dp)
        tabs.getTabAt(1)?.text = "Statistika"
    }

}
