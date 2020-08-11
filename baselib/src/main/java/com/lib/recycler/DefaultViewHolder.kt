package com.lib.recycler

import android.view.View

abstract class DefaultViewHolder<T>(view: View) : BaseViewHolder<T>(view) {

    override fun bindData(position: Int) {
        //do nothing
    }

    override fun initView(itemView: View) {
        //do nothing
    }


}