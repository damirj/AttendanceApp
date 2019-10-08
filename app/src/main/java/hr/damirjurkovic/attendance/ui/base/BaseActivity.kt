package hr.damirjurkovic.attendance.ui.base

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import hr.damirjurkovic.attendance.common.gone
import hr.damirjurkovic.attendance.common.visible

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        setUpUi()
    }

    protected abstract fun setUpUi()

    protected abstract fun getLayoutRes(): Int

    fun showLoading(progressBar: ProgressBar) = progressBar.visible()

    fun hideLoading(progressBar: ProgressBar) = progressBar.gone()

}