package com.example.appstudents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import com.example.appstudents.data.Student
import java.util.*

class StudentInfoFragment : Fragment() {
    private lateinit var studentInfoViewModel: StudentListViewModel
    private lateinit var lastNameTextView: EditText
    private lateinit var firstNameTextView: TextView
    private lateinit var middleNameTextView: TextView
    private lateinit var facultyTextView: TextView
    private lateinit var groupTextView: TextView
    private lateinit var datePicker: DatePicker
    private lateinit var saveBtn: Button
    private lateinit var CancelBtn: Button

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
        val view=inflater.inflate(R.layout.student_info, container, false)
        lastNameTextView=view.findViewById(R.id.LastNameTextView)
        firstNameTextView=view.findViewById(R.id.firstNameTextView)
        middleNameTextView=view.findViewById(R.id.MiddleNameTextView)
        facultyTextView=view.findViewById(R.id.FacultyTextView)
        groupTextView=view.findViewById(R.id.GroupTextView)
        datePicker=view.findViewById(R.id.datePicker)
        saveBtn=view.findViewById(R.id.saveBtn)
        saveBtn.setOnClickListener { saveStudent() }
        CancelBtn=view.findViewById(R.id.CancelBtn)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentInfoViewModel = ViewModelProvider(this).get(studentInfoViewModel::class.java)
        studentInfoViewModel.student.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

//    fun saveStudent() {
//        val dateBirth = GregorianCalendar(datePicker.year, datePicker.month, datePicker.dayOfMonth)
//        studentInfoViewModel.save(
//    ): View? {
//            val view= inflater.inflate(R.layout.student_info, container, false)
//            lastNameTextView=view.findViewById(R.id.lastName)
//            lastFirstName=view.findVIewById(R.id.firstNameTextView)
//    }

    fun saveStudent() {
        val dateBirth = GregorianCalendar(datePicker.year, datePicker.month, datePicker.dayOfMonth)
        studentInfoViewModel.save(
            lastNameTextView.text.toString(),
            firstNameTextView.text.toString(),
            middleNameTextView.text.toString(),
            dateBirth.time,
            facultyTextView.text.toString(),
            groupTextView.text.toString()
        )
    }

    fun updateUI(student: Student){
        lastNameTextView.setText(student.lastName)
        firstNameTextView.setText(student.firstName)
        middleNameTextView.setText(student.middleName)
        val dateBirth = GregorianCalendar()
        dateBirth.time = student.birthDate
        datePicker.updateDate(dateBirth.get(Calendar.YEAR), dateBirth.get(Calendar.MONTH), dateBirth.get())
    }


}