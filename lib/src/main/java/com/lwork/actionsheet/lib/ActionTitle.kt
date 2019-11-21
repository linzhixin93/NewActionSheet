package com.lwork.actionsheet.lib

import android.content.Context
import android.view.View

/**
 * author：LinZhiXin
 * date：2019/11/21
 * description：标题选项
 **/

class ActionTitle constructor(
    text: String? = null,
    subText: String? = null,
    leftIcon: Int? = null,
    tag: Any? = null,
    actionStyle: ActionStyle = ActionStyle()
) : ActionOption(text, subText, leftIcon, tag, actionStyle) {

    override fun buildOptionView(context: Context, topCornerBg: Boolean): View {
        return super.buildOptionView(context, topCornerBg).apply {
            setBackgroundResource(R.drawable.title_top_border_item_bg)
        }
    }
}