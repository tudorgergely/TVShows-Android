package com.example.tgergely.tvshows

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewManager
import android.widget.ImageView
import com.example.tgergely.tvshows.models.TvShow
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import java.net.URL

class ListItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListItemComponent().setContentView(this)
    }
}

class ListItemComponent : AnkoComponent<ListItem> {
    override fun createView(ui: AnkoContext<ListItem>): View = with(ui) {
        val tvShow = ui.owner.intent.getSerializableExtra("item") as TvShow
        verticalLayout {
            imageView {
                scaleType = ImageView.ScaleType.CENTER
                doAsync {
                    val bytes = URL(tvShow.photoUrl).readBytes()
                    val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    uiThread {
                        imageBitmap = Bitmap.createScaledBitmap(bmp, 500, 500, true)
                    }
                }
            }
            textView(text = tvShow.name).lparams(width = matchParent)
            radarChart {
                data = RadarData(RadarDataSet(listOf(RadarEntry(12.0F), RadarEntry(2.0F), RadarEntry(6.0F)), "label"))
                webLineWidth = 12f
                webLineWidthInner = 0.75f
                webAlpha = 100
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}

inline fun ViewManager.radarChart(init: RadarChart.() -> Unit) =
        ankoView({ RadarChart(it) }, 0, init)