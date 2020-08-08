/*
 * Copyright (c) 2015-2019 Uli Bubenheimer. All rights reserved.
 */
package org.bubenheimer.sample

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MyDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Welcome")
            .create()
    }
}
