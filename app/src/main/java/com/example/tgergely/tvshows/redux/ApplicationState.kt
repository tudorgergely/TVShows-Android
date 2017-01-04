package com.example.tgergely.tvshows.redux

import com.example.tgergely.tvshows.models.TvShow

data class ApplicationState(
        val items: List<TvShow> = emptyList(),
        val contactMessage: String = ""
)