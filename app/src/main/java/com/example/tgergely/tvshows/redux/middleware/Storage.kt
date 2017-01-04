package com.example.tgergely.tvshows.redux.middleware

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.ducks.Home.ITEM_FAVORITE
import com.orhanobut.hawk.Hawk

/**
 * Created by tudorgergely on 9/01/17.
 */

val FAVORITES_KEY = "@@tvShows-favorites"

val storage = Middleware<ApplicationState> { store, action, next ->
    val a = 2
    when (action) {
        is ITEM_FAVORITE -> {
            val favorites = Hawk.get<List<TvShow>>(FAVORITES_KEY) ?: emptyList()
            val tvShow = action.item

            Hawk.put<List<TvShow>>(FAVORITES_KEY, favorites.plus(tvShow).distinctBy { it.name })
            next(action)
        }
        else -> next(action)
    }
}