package com.example.tgergely.tvshows.models

import java.io.Serializable

/**
 * Created by tudorgergely on 5/01/17.
 */

data class TvShow(
        val name: String,
        val genres: List<String>,
        val photoUrl: String,
        val favorite: Boolean = false
) : Serializable