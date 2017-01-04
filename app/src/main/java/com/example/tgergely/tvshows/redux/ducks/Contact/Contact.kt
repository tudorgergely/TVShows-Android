package com.example.tgergely.tvshows.redux.ducks.Contact

import com.brianegan.bansa.Action
import com.brianegan.bansa.BaseStore
import com.brianegan.bansa.Reducer
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.middleware.itemsLoad


data class SEND_CONTACT_EMAIL(val body: String) : Action
object SEND_SUCCESSFUL : Action
object SEND_FAILED : Action

private val reducer = Reducer<ApplicationState> { state, action ->
    when (action) {
        is SEND_CONTACT_EMAIL -> ApplicationState(contactMessage = action.body)
        else -> state
    }
}

//val homeStore = BaseStore(ApplicationState(), reducer, itemsLoad)