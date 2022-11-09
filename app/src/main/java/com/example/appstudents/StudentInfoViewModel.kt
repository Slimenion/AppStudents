package com.example.appstudents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appstudents.data.Student
import com.example.appstudents.repository.StudentRepository
import java.util.*

class StudentInfoViewModel : ViewModel() {
    var student: MutableLiveData<Student> = MutableLiveData()
    init {
        StudentRepository.getInstance().student.observeForever{
            student.postValue(it)
        }
    }

    fun save(lastName: String="",
             firstName: String="",
             middleName: String="",
             birthDate: Date = Date(),
             faculty: String="",
             group: String="") {
        if (student.value == null) student.value = Student()
        student.value!!.lastName = lastName
        student.value!!.firstName = firstName
        student.value!!.middleName=middleName
        student.value!!.birthDate= birthDate
        student.value!!.faculty = faculty
        student.value!!.group = group
        StudentRepository.getInstance().updateStudent(student.value!!)
    }
}