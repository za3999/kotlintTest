package com.lib.recycler

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class BaseRecyclerView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RecyclerView(context, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    var mAdapter: BaseRecyclerAdapter<*>? = null

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mAdapter?.onDetachedAllFromWindow()
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        mAdapter = if (adapter is BaseRecyclerAdapter<*>) adapter else null
    }
}