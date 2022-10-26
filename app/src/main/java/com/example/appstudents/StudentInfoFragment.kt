package com.example.appstudents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class StudentInfoFragment : Fragment() {
    private lateinit var studentInfoViewModel: StudentListViewModel

    companion object {
        private var INSTANCE: StudentInfoFragment? = null
        fun getInstance(): StudentInfoFragment {
            if (INSTANCE == null) {
                INSTANCE = StudentInfoFragment()
            }

            return INSTANCE?: throw IllegalStateException("Отображение не создано!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentInfoViewModel = ViewModelProvider(this).get(studentInfoViewModel::class.java)
    }



}