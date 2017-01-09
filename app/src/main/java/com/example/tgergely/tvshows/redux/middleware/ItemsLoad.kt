package com.example.tgergely.tvshows.redux.middleware

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_INIT
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_LOAD_COMPLETE
import com.orhanobut.hawk.Hawk

val itemsLoad = Middleware<ApplicationState> { store, action, next ->
    when (action) {
        is ITEMS_INIT -> {
            val items = arrayListOf(
                    TvShow("House1", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House2", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House3", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House4", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House5", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House6", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House7", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House8", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House9", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House0", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House11", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House12", emptyList(), "http://lorempixel.com/500/500/"),
                    TvShow("House13", emptyList(), "http://lorempixel.com/500/500/")
            )
            val favorites = Hawk.get<List<TvShow>>(FAVORITES_KEY) ?: emptyList()
            val itemWithFav = items.map { i -> TvShow(i.name, i.genres, i.photoUrl, i.name in favorites.map(TvShow::name)) }
            next(ITEMS_LOAD_COMPLETE(itemWithFav))
        }
        else -> next(action)
    }
}
