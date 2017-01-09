package com.example.tgergely.tvshows.redux.ducks.Home

import com.brianegan.bansa.Action
import com.brianegan.bansa.BaseStore
import com.brianegan.bansa.Reducer
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.middleware.firebaseStorage
import com.example.tgergely.tvshows.redux.middleware.itemsLoad
import com.example.tgergely.tvshows.redux.middleware.search
import com.example.tgergely.tvshows.redux.middleware.storage

object ITEMS_INIT : Action
data class ITEMS_LOAD_COMPLETE(val items: List<TvShow>) : Action
data class ITEM_FAVORITE(val item: TvShow) : Action
data class SEARCH_TV_SHOW(val query: String) : Action

private val reducer = Reducer<ApplicationState> { state, action ->
    when (action) {
        is ITEMS_INIT -> ApplicationState()
        is ITEMS_LOAD_COMPLETE -> ApplicationState(items = action.items)
        is SEARCH_TV_SHOW -> state
        is ITEM_FAVORITE -> state
        else -> state
    }
}

val homeStore = BaseStore(ApplicationState(), reducer, itemsLoad, storage, search, firebaseStorage)