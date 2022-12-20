package com.android.ctmanager.adapter

import android.util.Log
import com.android.ctmanager.Menu
import com.android.ctmanager.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class MenuAdapter(layoutResId: Int, data: MutableList<Menu>?) :
    BaseQuickAdapter<Menu, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: Menu) {
        holder.setText(R.id.tv_name, item.name)
       Log.e("MenuAdapter","name:"+item.name)
    }

}

