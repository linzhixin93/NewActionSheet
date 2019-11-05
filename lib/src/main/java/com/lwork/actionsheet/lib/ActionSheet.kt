package com.lwork.actionsheet.lib

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

/**
 * author：LinZhiXin
 * date：2019/10/31
 * description：
 **/

class ActionSheet {

    lateinit var optionList: List<ActionOption>

    var actionListener: IOnActionClickListener? = null

    var cancelText = "取消"

    fun show(parentView : View) {
        val popupWindow = PopupWindow()
        val contentView = LayoutInflater.from(parentView.context).inflate(R.layout.action_sheet_dialog, null)
        contentView.findViewById<View>(R.id.action_bg).setOnClickListener {
            popupWindow.dismiss()
        }
        contentView.findViewById<TextView>(R.id.action_sheet_cancel_tv).run {
            text = cancelText
            setOnClickListener {
                popupWindow.dismiss()
            }
        }
        val optionLayout = contentView.findViewById<LinearLayout>(R.id.action_sheet_option_ll)
        optionList.forEachIndexed { index, actionOption ->
            optionLayout.addView(
                actionOption.buildOptionView(parentView.context!!, index == 0).apply {
                    setOnClickListener {
                        actionListener?.onActionClick(actionOption, index)
                    }
                },
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    if (actionOption.actionStyle.height != 0)
                        actionOption.actionStyle.height
                    else
                        parentView.resources.getDimensionPixelSize(if (actionOption.leftIcon == null) R.dimen.default_height else R.dimen.default_height)
                )
            )
        }
        popupWindow.contentView = contentView
        popupWindow.isOutsideTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.height = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.isFocusable = true
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0)
    }


}

