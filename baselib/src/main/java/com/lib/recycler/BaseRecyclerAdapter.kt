package com.lib.recycler

import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val result: MutableList<T> = mutableListOf()
    lateinit var mRecyclerView: RecyclerView
    lateinit var itemClick: (Int, View, T?) -> Unit?
    lateinit var itemLongClick: (Int, View, T?) -> Unit?

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        onDetachedAllFromWindow()
    }

    fun onDetachedAllFromWindow() {
        val count = mRecyclerView?.childCount
        for (i in 0 until count) {
            val holder: BaseViewHolder<T> = mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(i)) as BaseViewHolder<T>
            holder.onDetachedFromWindow()
        }
    }

    @CallSuper
    override fun onViewAttachedToWindow(holder: BaseViewHolder<T>) {
        holder.onAttachedToWindow()
    }

    @CallSuper
    override fun onViewDetachedFromWindow(holder: BaseViewHolder<T>) {
        holder.onDetachedFromWindow()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < result.size && result[position] is MultiItem<*>) {
            (result[position] as MultiItem<*>).type
        } else 0
    }

    override fun getItemCount(): Int {
        return result.size
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        var holder = createViewHolderWrapper(parent, viewType)
        holder.itemClick = { view: View, item: T? ->
            onItemClick(getPosition(item), view, item)
        }
        holder.itemLongClick = { view: View, item: T? ->
            onItemLongClick(getPosition(item), view, item)
        }
        return holder
    }

    abstract fun createViewHolderWrapper(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.itemData = getItem(position)
        holder.bindData(position)
    }

    fun setData(list: List<T>) {
        result.clear()
        if (list != null && !list.isEmpty()) {
            result.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun addData(list: List<T>) {
        result.addAll(list)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T? {
        return if (position < result.size) result[position] else null
    }

    open fun onItemClick(position: Int, v: View, item: T?) {
        itemClick?.invoke(position, v, item)
    }

    open fun onItemLongClick(position: Int, v: View, item: T?) {
        itemLongClick?.invoke(position, v, item)
    }

    private fun getPosition(t: T?): Int {
        var position = 0
        val size = result.size
        for (i in 0 until size) {
            if (result[i] === t) {
                position = i
                break
            }
        }
        return position
    }

}