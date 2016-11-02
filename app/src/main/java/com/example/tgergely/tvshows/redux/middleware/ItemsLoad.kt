package com.example.tgergely.tvshows.redux.middleware

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_INIT
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_LOAD_COMPLETE

val itemsLoad = Middleware<ApplicationState> { store, action, next ->
    when (action) {
        is ITEMS_INIT -> {
            next(ITEMS_LOAD_COMPLETE(arrayListOf("a", "b", "c", "d")))
        }
        else -> next(action)
    }
}
