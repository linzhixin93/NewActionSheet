package com.lwork.actionsheet

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.lwork.actionsheet.lib.ActionOption
import com.lwork.actionsheet.lib.ActionSheet

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onShow(view : View) {
        ActionSheet().apply {
            optionList = listOf(
                ActionOption("ssss", "sss"),
                ActionOption("ssss", "sss"),
                ActionOption("ssss", "sss"),
                ActionOption("wwww"),
                ActionOption("qqq", leftIcon = R.drawable.default_user)
            )
        }.show(this)
    }
}
