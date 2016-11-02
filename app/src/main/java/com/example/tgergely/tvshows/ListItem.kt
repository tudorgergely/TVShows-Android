package com.example.tgergely.tvshows

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.widget.EditText

class ListItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)

        val item = intent.getStringExtra("item")
        val editText = findViewById(R.id.editText) as EditText
        editText.text = SpannableStringBuilder().append(item)
    }

}
