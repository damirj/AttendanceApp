package hr.damirjurkovic.attendance.common

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import hr.damirjurkovic.attendance.R

fun FragmentActivity.showFragment(
    containerId: Int,
    fragment: Fragment,
    shouldAddToBackStack: Boolean = false,
    tag: String = ""
) {
    supportFragmentManager.beginTransaction().apply {
        if (shouldAddToBackStack) {
            addToBackStack(tag)
        }
    }.replace(containerId, fragment).commitAllowingStateLoss()
}

fun Context.showYesNoDialog(
    message: String = getString(R.string.areYouSure),
    positiveReply: () -> Unit,
    negativeReply: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message).setCancelable(true)
    builder.setPositiveButton(getString(R.string.positiveReply)) { dialog, _ ->
        run {
            positiveReply()
            dialog.cancel()
        }
    }

    builder.setNegativeButton(getString(R.string.negativeReply)) { dialog, _ ->
        run {
            negativeReply()
            dialog.cancel()
        }
    }

    builder.create().show()
}
