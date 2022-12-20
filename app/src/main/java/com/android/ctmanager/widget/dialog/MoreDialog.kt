package com.android.ctmanager.widget.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import com.android.ctmanager.R

class MoreDialog(
    context: Context,
) :
    Dialog(context, R.style.more_dialog_stye) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_dialog_layout)
        setUiBeforeShow()
    }

    private fun setUiBeforeShow() {
        val attrs = window?.attributes
        attrs?.width = WindowManager.LayoutParams.MATCH_PARENT
        attrs?.height = WindowManager.LayoutParams.WRAP_CONTENT
        attrs?.x = 0
        attrs?.y = 200
        window?.setDimAmount(0f)
        window?.attributes = attrs

      //  window?.setWindowAnimations(R.style.main_AnimBottom)
     //   window?.setGravity(Gravity.BOTTOM)


        }


    }
