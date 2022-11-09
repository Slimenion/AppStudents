package com.example.appstudents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    private var niAdd: MenuItem? = null
    private var niDelete: MenuItem? = null
    private var niChange: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showStudentList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        niAdd=menu.findItem(R.id.mvAdd)
        niDelete=menu.findItem(R.id.mvDelete)
        niChange=menu.findItem(R.id.mvChange)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mvAdd -> {
                showNestedStudent()
                true
            }
            R.id.mvDelete -> {
                true
            }
            R.id.mvChange -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}