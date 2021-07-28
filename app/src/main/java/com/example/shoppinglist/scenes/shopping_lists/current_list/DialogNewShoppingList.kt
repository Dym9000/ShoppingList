package com.example.shoppinglist.scenes.shopping_lists.current_list

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResult
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.DialogNewShoppingListBinding
import com.example.shoppinglist.scenes.shopping_lists.current_list.utils.ConstantsCurrentList
import com.example.shoppinglist.util.DateFormatter
import java.util.*

class DialogNewShoppingList : AppCompatDialogFragment(), FragmentResultListener {

    private lateinit var binding: DialogNewShoppingListBinding
    private lateinit var shoppingDate: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding = DialogNewShoppingListBinding.inflate(inflater, null, false)

            builder.setView(binding.root)
                .setTitle(getString(R.string.dialog_new_list_title))
                .setPositiveButton(
                    R.string.dialog_new_list_positive_btn
                ) { _, _ ->
                    val newListName = binding.enterName.text.toString()

                    setFragmentResult(
                        ConstantsCurrentList.NEW_LIST_DIALOG_KEY,
                        bundleOf(
                            ConstantsCurrentList.BUNDLE_NAME_KEY to newListName,
                            ConstantsCurrentList.BUNDLE_DATE_KEY to shoppingDate
                        )
                    )
                }
                .setNegativeButton(
                    R.string.dialog_new_list_cancel_btn
                ) { _, _ ->
                    dialog?.cancel()
                }

            childFragmentManager.setFragmentResultListener(
                ConstantsCurrentList.DATE_PICKER_KEY,
                this,
                this
            )

            setDateButtonListener()

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }

    override fun onFragmentResult(requestKey: String, result: Bundle) {
        val year = result.getInt(ConstantsCurrentList.BUNDLE_DATE_YEAR_KEY)
        val month = result.getInt(ConstantsCurrentList.BUNDLE_DATE_MONTH_KEY)
        val day = result.getInt(ConstantsCurrentList.BUNDLE_DATE_DAY_KEY)
        shoppingDate = Calendar.getInstance()
        shoppingDate.set(Calendar.YEAR, year)
        shoppingDate.set(Calendar.MONTH, month)
        shoppingDate.set(Calendar.DATE, day)

        binding.displayDate.text = DateFormatter.formatDate(shoppingDate.time)
    }

    private fun setDateButtonListener() {
        binding.setDateBtn.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.show(childFragmentManager, ConstantsCurrentList.DATE_PICKER_TAG)
        }
    }
}