package com.example.tgergely.tvshows.redux.ducks.Home

import com.brianegan.bansa.Action
import com.brianegan.bansa.BaseStore
import com.brianegan.bansa.Reducer
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.middleware.itemsLoad

object ITEMS_INIT : Action
data class ITEMS_LOAD_COMPLETE(val items: List<String>) : Action

private val reducer = Reducer<ApplicationState> { state, action ->
    when (action) {
        is ITEMS_INIT -> ApplicationState()
        is ITEMS_LOAD_COMPLETE -> ApplicationState(items = action.items)
        else -> state
    }
}

val homeStore = BaseStore(ApplicationState(), reducer, itemsLoad)