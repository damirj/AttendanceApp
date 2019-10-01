package hr.damirjurkovic.attendance.common

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

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
    message: String = "Are you sure?",
    positiveReply: () -> Unit,
    negativeReply: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
    builder.setMessage(message).setCancelable(true)
    builder.setPositiveButton("Yes") { dialog, _ ->
        run {
            positiveReply()
            dialog.cancel()
        }
    }

    builder.setNegativeButton("No") { dialog, _ ->
        run {
            negativeReply()
            dialog.cancel()
        }
    }

    builder.create().show()
}
