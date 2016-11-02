package com.example.tgergely.tvshows.containers


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.tgergely.tvshows.ListItem

import com.example.tgergely.tvshows.R
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_INIT
import com.example.tgergely.tvshows.redux.ducks.Home.homeStore

class Home : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    private var mItemsListView: ListView? = null
    private var mItemsList: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)
        mItemsListView = view!!.findViewById(R.id.list) as ListView
        homeStore.subscribe({
            mItemsList = homeStore.state.items
        })
        homeStore.dispatch(ITEMS_INIT)
        mItemsListView?.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, android.R.id.text1, mItemsList)
        mItemsListView?.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(context, ListItem::class.java)
            intent.putExtra("item", mItemsList[i])
            startActivity(intent)
        }
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context as OnFragmentInteractionListener?
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
    }
}
