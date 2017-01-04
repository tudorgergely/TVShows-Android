package com.example.tgergely.tvshows.redux.middleware

import com.brianegan.bansa.Middleware
import com.brianegan.bansaKotlin.invoke
import com.example.tgergely.tvshows.redux.ApplicationState
import com.example.tgergely.tvshows.redux.ducks.Contact.SEND_CONTACT_EMAIL
import com.example.tgergely.tvshows.redux.ducks.Contact.SEND_SUCCESSFUL

val contactEmail = Middleware<ApplicationState> { store, action, next ->
    when (action) {
        is SEND_CONTACT_EMAIL -> {
            next(SEND_SUCCESSFUL)
        }
        else -> next(action)
    }
}
