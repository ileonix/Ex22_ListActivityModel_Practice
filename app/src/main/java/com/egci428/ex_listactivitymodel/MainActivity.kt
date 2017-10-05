package com.egci428.ex_listactivitymodel

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.course_item.view.*


class MainActivity : AppCompatActivity() {

    val DETAIL_REQUEST_CODE = 1001
    protected var data: ArrayList<Course>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = DataProvider.getData() //โหลดข้อมูลเข้ามาจาก DataProvider.kt
        val courseArrayAdapter = CourseArrayAdapter(this,0,data!!)
        list.setAdapter(courseArrayAdapter)

        list.setOnItemClickListener{adapterView, view, position, id ->
            val course = data!!.get(position)
            displayDetail(course,position)
        }
    }

    private fun displayDetail(course: Course, position: Int) {
        val intent = Intent(this,DetailActivity::class.java)
        val imgpos = position%3+1
        intent.putExtra("courseTitle",course.title) //data!![]
        intent.putExtra("courseDesc",course.description)
        intent.putExtra("courseNum",course.courseNumber.toString())
        intent.putExtra("courseCre",course.credits.toString())
        intent.putExtra("imagepo",imgpos.toString())
        startActivityForResult(intent,DETAIL_REQUEST_CODE)
    }

    private class CourseArrayAdapter(var context: Context, resource: Int, var objects: ArrayList<Course>) : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View //row main ของแต่ละแถว
            val course = objects[position]
            val yellowColor = Color.parseColor("#FFFFCC")
            val greyColor = Color.parseColor("#778899")
            if (convertView == null){
                val layoutInflator = LayoutInflater.from(parent!!.context)//ต้องประกาศ layout inflater แบบประหยัดทรัพยากร parent!!(not null)
                view = layoutInflator.inflate(R.layout.course_item,parent,false)
                val viewHolder = ViewHolder(view.titleText,view.imageCourse)
                view.tag = viewHolder
            }else{
                view = convertView
            }
            val viewHolder = view.tag as ViewHolder //เป็นตัวแปรคนละตัวกับ viewHolder = ViewHolder(view.titleText,view.imageCourse) ใน if
            viewHolder.titleTextView.text =  course.title //objects[position]
            val imgpos = position%3+1 //+1 เพราะ index มันเริ่มที่ 0
            val res = context.resources.getIdentifier("image1010"+imgpos,"drawable",context.packageName)
            viewHolder.imageCourse.setImageResource(res)
            if(position%2 == 0){
                view.setBackgroundColor(yellowColor)
            }else{
                view.setBackgroundColor(greyColor)
            }
            return view
        }

        override fun getCount(): Int {
            return objects.size //obj เป็น arrayList<Course> จึงคืนค่าเป็น size
        }

        override fun getItem(position: Int): Any {
            return objects[position]//คืนค่าในตำแหน่งนั้นๆ
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        //ViewHolder ทำให้โค้ดเป็นระเบียบ
        private class ViewHolder(val titleTextView: TextView,val imageCourse: ImageView)
    }
}
