package com.test.kotlin.kotlintest.test.common.test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lib.recycler.BaseRecyclerAdapter
import com.lib.recycler.BaseViewHolder
import com.lib.recycler.DefaultViewHolder
import com.test.kotlin.kotlintest.R
import com.test.kotlin.kotlintest.test.common.Constants
import com.test.kotlin.kotlintest.test.common.test.bean.User
import com.test.kotlin.kotlintest.test.common.test.util.ExtrasDelegate
import kotlinx.android.synthetic.main.recycler_view_layout.*

fun createTextHolderView(parent: ViewGroup, height: Int): View {
    val view = TextView(parent.context)
    view.layoutParams = AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
    return view
}

class ExtrasActivity : AppCompatActivity() {
    private var user: User? by ExtrasDelegate("user", null)
    private var string: String? by ExtrasDelegate("string", "test")
    private lateinit var mAdapter: BaseRecyclerAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(Constants.TAG, "ExtrasActivity user:$user,string:$string")
        string = "哈哈哈"
        Log.d(Constants.TAG, "ExtrasActivity user:$user,string:$string")
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
        recyclerView.mAdapter = mAdapter
        mAdapter.addData(mutableListOf("aaa", "bbb", "ccc"))
    }
}