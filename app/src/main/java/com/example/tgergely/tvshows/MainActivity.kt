package com.example.tgergely.tvshows

import android.app.Fragment
import android.app.Notification
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.tgergely.tvshows.containers.Contact
import com.example.tgergely.tvshows.containers.Home
import com.orhanobut.hawk.Hawk
import org.jetbrains.anko.doAsync
import java.util.*
import android.content.Context.NOTIFICATION_SERVICE
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import org.jetbrains.anko.notificationManager


class MainActivity : AppCompatActivity(), Home.OnFragmentInteractionListener, Contact.OnFragmentInteractionListener {

    private var mDrawerFragments: List<Fragment> = Arrays.asList(Home(), Contact())
    private var mDrawerTitles: List<String>? = Arrays.asList("Home", "Contact")
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerList: ListView? = null

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_main)
        Hawk.init(applicationContext).build()
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_template_icon_bg)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
        val resultIntent = Intent(this, MainActivity::class.java)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)

        notificationManager.notify(0, mBuilder.build())

        val fm = fragmentManager
        val ft = fm.beginTransaction()

        val fragOne = Home()
        ft.add(R.id.content_frame, fragOne)
        ft.commit()

        mDrawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        mDrawerList = findViewById(R.id.left_drawer) as ListView

        mDrawerList!!.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mDrawerTitles)
        mDrawerList!!.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l -> selectItem(i) }
    }

    private fun selectItem(position: Int) {
        fragmentManager.beginTransaction().replace(R.id.content_frame, mDrawerFragments[position]).commit()
        mDrawerList!!.setItemChecked(position, true)
        title = mDrawerTitles!![position]
        mDrawerLayout!!.closeDrawer(mDrawerList)
    }
}