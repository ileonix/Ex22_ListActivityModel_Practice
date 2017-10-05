package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import android.view.Menu
import java.text.NumberFormat


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bundle = intent.extras
        val title: String = bundle.getString("courseTitle")
        val description: String = bundle.getString("courseDesc")
        val courseNum: String = bundle.getString("courseNum")
        val courseCre: String = bundle.getString("courseCre")
        val imgpo: Int = bundle.getString("imagepo").toInt()

        titleText.text = title
        descriptionText.text = description
        courseNoText.text = courseNum
        creditsText.text = courseCre

        val res = resources.getIdentifier("image1010"+imgpo,"drawable",packageName)
        imageCourse.setImageResource(res)


    }
}
