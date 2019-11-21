package com.lwork.actionsheet.lib

import android.app.Activity
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

    var actionTitle : ActionTitle ?= null

    var actionListener: IOnActionClickListener? = null

    var cancelText = "取消"

    var windowAlpha = 0.3f

    fun show(activity : Activity) {
        val popupWindow = PopupWindow()
        val contentView = LayoutInflater.from(activity).inflate(R.layout.action_sheet_dialog, null)
        contentView.findViewById<TextView>(R.id.action_sheet_cancel_tv).run {
            text = cancelText
            setOnClickListener {
                popupWindow.dismiss()
            }
        }
        val optionLayout = contentView.findViewById<LinearLayout>(R.id.action_sheet_option_ll)

        actionTitle?.let {
            optionLayout.addView(
                it.buildOptionView(activity, true),
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    if (it.actionStyle.height != 0)
                        it.actionStyle.height
                    else
                        activity.resources.getDimensionPixelSize(if (it.leftIcon == null) R.dimen.default_height else R.dimen.default_height)
                )
            )
        }
        optionList.forEachIndexed { index, actionOption ->
            optionLayout.addView(
                actionOption.buildOptionView(activity, index == 0 && actionTitle == null).apply {
                    setOnClickListener {
                        popupWindow.dismiss()
                        actionListener?.onActionClick(actionOption, index)
                    }
                },
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    if (actionOption.actionStyle.height != 0)
                        actionOption.actionStyle.height
                    else
                        activity.resources.getDimensionPixelSize(if (actionOption.leftIcon == null) R.dimen.default_height else R.dimen.default_height)
                )
            )
        }
        popupWindow.contentView = contentView
        popupWindow.isOutsideTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        popupWindow.isFocusable = true
        popupWindow.animationStyle = R.style.popupAnimationStyle
        popupWindow.setOnDismissListener {
            setWindowAlpha(activity, 1.0f)
        }
        popupWindow.showAtLocation(activity.window.decorView, Gravity.BOTTOM, 0, 0)
        setWindowAlpha(activity, windowAlpha)
    }

    private fun setWindowAlpha(activity: Activity, alpha: Float) {
        val window = activity.window
        val params = window.attributes
        params.alpha = alpha
        window.attributes = params
    }


}

