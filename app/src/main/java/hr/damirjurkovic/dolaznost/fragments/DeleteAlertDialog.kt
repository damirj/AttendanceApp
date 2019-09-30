package hr.damirjurkovic.dolaznost.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView

class DeleteAlertDialog: DialogFragment() {

    private var deleteDialogListener: DeleteDialogListener? = null
    private var viewHolderDelete: RecyclerView.ViewHolder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setCanceledOnTouchOutside(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    interface DeleteDialogListener{
        fun onYesClicked(viewHolder: RecyclerView.ViewHolder?)
        fun onNoClicked()
    }

    fun setDeleteDialogListener(listener: DeleteDialogListener){
        deleteDialogListener = listener
    }

    fun setDeleteTask(viewHolder: RecyclerView.ViewHolder){
        viewHolderDelete = viewHolder
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are you sure?")
        builder.setCancelable(true)

        builder.setPositiveButton("YES!") { dialog, _ ->
            run {
                deleteDialogListener?.onYesClicked(viewHolderDelete)
                dialog.cancel()
            }
        }
        builder.setNegativeButton("NO!") { dialog, _ ->
            run {
                deleteDialogListener?.onNoClicked()
                dialog.cancel()
            }
        }
        return builder.create()
    }
}