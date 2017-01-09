package com.example.tgergely.tvshows.redux.middleware

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.ducks.Home.ITEMS_LOAD_COMPLETE
import com.example.tgergely.tvshows.redux.ducks.Home.ITEM_FAVORITE
import com.example.tgergely.tvshows.redux.ducks.Home.SEARCH_TV_SHOW
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.orhanobut.hawk.Hawk
import org.json.JSONObject
import java.util.*

/**
 * Created by tudorgergely on 9/01/17.
 */

val search = Middleware<ApplicationState> { store, action, next ->
    val a = 2
    when (action) {
        is SEARCH_TV_SHOW -> {
            "http://www.omdbapi.com/?s=${action.query}&r=json".httpGet().responseJson { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                    }
                    is Result.Success -> {
                        val results = result.get().obj().getJSONArray("Search")
                        val resultsArray = ArrayList<JSONObject>()

                        for (i in 0..(results.length() - 1)) {
                            resultsArray.add(results.getJSONObject(i))
                        }

                        val tvShows = resultsArray.map(::responseToTvShow)
                        next(ITEMS_LOAD_COMPLETE(tvShows))
                    }
                }
            }
        }
        else -> next(action)
    }
}

fun responseToTvShow(response: JSONObject): TvShow {
    return TvShow(
            response["Title"] as String,
            emptyList(),
            response["Poster"] as String
    )
}