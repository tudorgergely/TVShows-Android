package com.example.tgergely.tvshows

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.tgergely.tvshows.containers.Contact
import com.example.tgergely.tvshows.containers.Home
import java.util.*

class MainActivity : AppCompatActivity(), Home.OnFragmentInteractionListener, Contact.OnFragmentInteractionListener {

    private var mDrawerFragments: List<Fragment> = Arrays.asList(Home(), Contact())
    private var mDrawerTitles: List<String>? = Arrays.asList("Home", "Contact")
    private var mDrawerLayout: DrawerLayout? = null
    private var mDrawerList: ListView? = null

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setContentView(R.layout.activity_main)

        val fm = supportFragmentManager
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
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, mDrawerFragments[position]).commit()
        mDrawerList!!.setItemChecked(position, true)
        title = mDrawerTitles!![position]
        mDrawerLayout!!.closeDrawer(mDrawerList)
    }
}