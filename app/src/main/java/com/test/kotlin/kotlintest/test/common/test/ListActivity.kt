package com.test.kotlin.kotlintest.test.common.test

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.recycler.BaseRecyclerAdapter
import com.lib.recycler.BaseViewHolder
import com.lib.recycler.DefaultViewHolder
import com.test.kotlin.kotlintest.R
import com.test.kotlin.kotlintest.test.common.dp
import kotlinx.android.synthetic.main.recycler_view_layout.*

class ListActivity : AppCompatActivity() {

    private lateinit var mAdapter: BaseRecyclerAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_layout)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = object : BaseRecyclerAdapter<String>() {
            override fun createViewHolderWrapper(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
                return object : DefaultViewHolder<String>(createTextHolderView(parent, resources.getDimension(R.dimen.dp_30).toInt())) {
                    override fun bindData(position: Int) {
                        (itemView as TextView).text = itemData;
                    }

                    override fun onItemClick(v: View, t: String?) {
                        Toast.makeText(v.context, t, Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
        recyclerView.adapter = mAdapter
        mAdapter.addData(mutableListOf("aaa", "bbb", "ccc"))
    }

    fun createTextHolderView(parent: ViewGroup, height: Int): View {
        val view = TextView(parent.context)
        view.layoutParams = AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
        view.gravity = Gravity.CENTER_VERTICAL
        view.setTextColor(Color.BLACK)
        view.setPadding(30.dp, 0, 0, 0)
        return view
    }
}