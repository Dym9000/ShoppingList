package com.example.shoppinglist.scenes.shopping_lists.list_details

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.DialogNewProductBinding
import com.example.shoppinglist.scenes.shopping_lists.list_details.utils.ConstantsListDetails

class DialogNewProduct : AppCompatDialogFragment() {

    private lateinit var binding: DialogNewProductBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding = DialogNewProductBinding.inflate(inflater, null, false)

            builder.setView(binding.root)
                .setTitle(getString(R.string.dialog_new_product_title))
                .setPositiveButton(
                    R.string.dialog_new_list_positive_btn
                ) { _, _ ->
                    val newProductName = binding.dialogNewProductEnterName.text.toString()
                    val newProductAmount = binding.dialogNewProductEnterAmount.text.toString()
                    setFragmentResult(
                        ConstantsListDetails.NEW_PRODUCT_DIALOG_KEY, bundleOf(
                            ConstantsListDetails.BUNDLE_NAME_KEY to newProductName,
                            ConstantsListDetails.BUNDLE_AMOUNT_KEY to newProductAmount
                        )
                    )
                }
                .setNegativeButton(
                    R.string.dialog_new_list_cancel_btn
                ) { _, _ ->
                    dialog?.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")


    }
}