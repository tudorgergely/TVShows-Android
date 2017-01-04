package com.example.tgergely.tvshows.adapters

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.tgergely.tvshows.R
import com.example.tgergely.tvshows.models.TvShow
import com.example.tgergely.tvshows.redux.ducks.Home.ITEM_FAVORITE
import com.example.tgergely.tvshows.redux.ducks.Home.homeStore
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import org.jetbrains.anko.uiThread
import java.net.URL


/**
 * Created by tudorgergely on 5/01/17.
 */

class TvShowsAdapter(context: Context?, tvShows: List<TvShow>) : ArrayAdapter<TvShow>(context, 0, tvShows) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tvShow = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.tv_show_list_item, parent, false)

        val itemName = view.findViewById(R.id.itemName) as TextView
        val imageView = view.findViewById(R.id.imageView) as ImageView
        val imageButton = view.find<ImageButton>(R.id.imageButton)
        imageButton.visibility = if (tvShow.favorite === true) View.INVISIBLE else View.VISIBLE
        itemName.text = tvShow.name
        doAsync {
            val bytes = URL(tvShow.photoUrl).readBytes()
            val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            uiThread {
                imageView.setImageBitmap(bmp)
            }
        }
        imageButton.onClick {
            homeStore.dispatch(ITEM_FAVORITE(tvShow))
            imageButton.visibility = View.INVISIBLE
        }
        return view
    }
}