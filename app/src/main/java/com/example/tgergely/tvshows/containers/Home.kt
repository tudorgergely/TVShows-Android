package com.example.tgergely.tvshows.containers


import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.example.tgergely.tvshows.ListItem
import com.example.tgergely.tvshows.R
import com.example.tgergely.tvshows.adapters.TvShowsAdapter
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_INIT
import com.example.tgergely.tvshows.redux.ducks.Home.homeStore
import org.jetbrains.anko.*

class Home : Fragment() {
    private var mItemsListView: ListView? = null
    private var mItemsList: List<TvShow> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        homeStore.subscribe({
            mItemsList = homeStore.state.items
        })
        homeStore.dispatch(ITEMS_INIT)
        return UI {
            verticalLayout {
                listView {
                    adapter = TvShowsAdapter(context, mItemsList)
                    onItemClick { adapterView, view, i, l ->
                        startActivity(intentFor<ListItem>("item" to mItemsList[i]))
                    }
                }
            }
        }.view
    }

    interface OnFragmentInteractionListener
}
