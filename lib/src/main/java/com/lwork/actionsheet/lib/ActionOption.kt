package com.lwork.actionsheet.lib

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

/**
 * author：LinZhiXin
 * date：2019/10/31
 * description：
 **/

open class ActionOption constructor(
    val text: String? = null,
    var subText: String? = null,
    var leftIcon: Int? = null,
    var tag: Any? = null,
    var actionStyle : ActionStyle = ActionStyle()
) {


    open fun buildOptionView(context : Context, topCornerBg : Boolean) : View {
        return if (leftIcon == null) {
            buildTextOptionView(context).apply {
                setBackgroundResource(if (topCornerBg) R.drawable.top_border_item_bg else R.drawable.normal_item_bg)
            }
        } else {
            buildIconOptionView(context).apply {
                setBackgroundResource(if (topCornerBg) R.drawable.top_border_item_bg else R.drawable.normal_item_bg)
            }
        }
    }

    private fun buildIconOptionView(
        context: Context
    ): View {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.action_option_icon, null)
        val title = view.findViewById<TextView>(R.id.action_option_icon_tv)
        val iconIv = view.findViewById<ImageView>(R.id.action_option_icon_iv)
        title.setTextColor(ContextCompat.getColor(context, actionStyle.textColor))
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, actionStyle.textSize)
        title.text = text
        leftIcon?.let { iconIv.setImageResource(it) }
        return view
    }

    private fun buildTextOptionView(
        context: Context
    ): View {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.action_option_text, null)
        val title = view.findViewById<TextView>(R.id.action_option_text_title_tv)
        val subTitle = view.findViewById<TextView>(R.id.action_option_text_sub_title_tv)
        title.setTextColor(ContextCompat.getColor(context, actionStyle.textColor))
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, actionStyle.textSize)
        title.text = text
        if (subText.isNullOrEmpty()) {
            subTitle.visibility = View.GONE
            return view
        }
        subTitle.setTextColor(ContextCompat.getColor(context, actionStyle.subTextColor))
        subTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, actionStyle.subTextSize)
        subTitle.text = subText
        return view
    }


}