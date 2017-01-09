package com.example.tgergely.tvshows.containers


import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.tgergely.tvshows.ListItem
import com.example.tgergely.tvshows.adapters.TvShowsAdapter
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_INIT
import com.example.tgergely.tvshows.redux.ducks.Home.SEARCH_TV_SHOW
import com.example.tgergely.tvshows.redux.ducks.Home.homeStore
import org.jetbrains.anko.*
import java.util.*

class Home : Fragment() {
    private var mItemsListView: ListView? = null
    private var mItemsList = ArrayList<TvShow>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val a = TvShowsAdapter(context, mItemsList)
        homeStore.subscribe({
            a.clear()
            a.addAll(homeStore.state.items)
        })
        homeStore.dispatch(ITEMS_INIT)
        return UI {
            verticalLayout {
                searchView {
                    onQueryTextListener {
                        onQueryTextSubmit { query -> homeStore.dispatch(SEARCH_TV_SHOW(query!!));false }
                    }
                }
                listView {
                    adapter = a
                    onItemClick { adapterView, view, i, l ->
                        startActivity(intentFor<ListItem>("item" to mItemsList[i]))
                    }
                }
            }
        }.view
    }

    interface OnFragmentInteractionListener
}
