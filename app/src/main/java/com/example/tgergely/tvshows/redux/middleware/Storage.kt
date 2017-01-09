package com.example.tgergely.tvshows.redux.middleware

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.ducks.Home.ITEM_FAVORITE
import com.google.firebase.database.FirebaseDatabase
import com.orhanobut.hawk.Hawk
import java.util.*

/**
 * Created by tudorgergely on 9/01/17.
 */

val FAVORITES_KEY = "@@tvShows-favorites"

val mDatabase = FirebaseDatabase.getInstance().getReference();

val storage = Middleware<ApplicationState> { store, action, next ->
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

val firebaseStorage = Middleware<ApplicationState> { store, action, nextDispatcher ->
    when (action) {
        is ITEM_FAVORITE -> {
            val favorites = Hawk.get<List<TvShow>>(FAVORITES_KEY) ?: emptyList()
            val tvShow = action.item
            val favs = favorites.plus(tvShow).distinctBy { it.name }

            val updates = HashMap<String, Any>()
            favs.forEach { fav ->
                updates.put("/favorites/" + fav.name, hashMapOf("name" to fav.name, "genres" to fav.genres, "photo_url" to fav.photoUrl))
            }

            mDatabase.database.getReference("favorites").updateChildren(updates)
            nextDispatcher(action)
        }
        else -> nextDispatcher(action)
    }
}