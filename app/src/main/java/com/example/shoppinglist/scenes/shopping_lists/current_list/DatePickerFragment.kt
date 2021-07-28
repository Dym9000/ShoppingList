package com.example.shoppinglist.scenes.shopping_lists.current_list

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.shoppinglist.scenes.shopping_lists.current_list.utils.ConstantsCurrentList
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(this.requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        setFragmentResult(
            ConstantsCurrentList.DATE_PICKER_KEY, bundleOf(
                ConstantsCurrentList.BUNDLE_DATE_YEAR_KEY to year,
                ConstantsCurrentList.BUNDLE_DATE_MONTH_KEY to month,
                ConstantsCurrentList.BUNDLE_DATE_DAY_KEY to day
            )
        )
    }
}