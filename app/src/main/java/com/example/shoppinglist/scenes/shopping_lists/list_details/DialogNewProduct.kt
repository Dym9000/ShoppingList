package com.example.shoppinglist.scenes.shopping_lists.list_details

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.DialogNewProductBinding

class DialogNewProduct(): AppCompatDialogFragment() {

    private lateinit var binding : DialogNewProductBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding = DialogNewProductBinding.inflate(inflater, null, false)

            builder.setView(binding.root)
                .setTitle(getString(R.string.dialog_new_list_title))
                .setPositiveButton(
                    R.string.dialog_new_list_positive_btn
                ) { dialog, id ->
//                    val newListName = editableText.text.toString()
                    val newProductName = binding.dialogNewProductEnterName.text.toString()
                    val newProductAmount = binding.dialogNewProductEnterAmount.text.toString()
                    setFragmentResult("name", bundleOf(
                        "name" to newProductName,
                        "name2" to newProductAmount))
                }
                .setNegativeButton(
                    R.string.dialog_new_list_cancel_btn
                ) { dialog, id ->
                    getDialog()?.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}