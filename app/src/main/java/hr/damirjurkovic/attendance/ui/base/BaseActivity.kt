package hr.damirjurkovic.attendance.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        setUpUi()
    }

    protected abstract fun setUpUi()

    protected abstract fun getLayoutRes(): Int
}