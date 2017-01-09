package com.example.tgergely.tvshows.redux.ducks.Contact

import com.brianegan.bansa.Action
import com.brianegan.bansa.BaseStore
import com.brianegan.bansa.Reducer
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.middleware.itemsLoad


data class LOGIN_SUCCESS(val email: String) : Action

private val reducer = Reducer<ApplicationState> { state, action ->
    when (action) {
        is LOGIN_SUCCESS -> ApplicationState(email = action.email)
        else -> state
    }
}

val securityStore = BaseStore(ApplicationState(), reducer)