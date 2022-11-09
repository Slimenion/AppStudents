package com.example.appstudents.repository

import android.icu.text.Transliterator
import androidx.lifecycle.MutableLiveData
import com.example.appstudents.data.Student
import com.example.appstudents.data.StudentsList

class StudentRepository {

    companion object {
        private var INSTANCE: StudentRepository? = null

        fun getInstance(): StudentRepository {
            if (INSTANCE == null) {
                INSTANCE = StudentRepository()
            }
            return INSTANCE ?:
            throw IllegalStateException("Репозиторий StudentRepository не инициализирован")
        }
    }

    val studentList: MutableLiveData<StudentsList> = MutableLiveData()

    var student: MutableLiveData<Student> = MutableLiveData()

    fun setCurrentStudent(position: Int) {
        if (studentList.value==null || studentList.value!!.items==null || position < 0 || (studentList.value?.items?.size!! <= position))
            return

        student.postValue(studentList.value?.items!![position])
    }

    fun addStudent(student: Student) {
        var studentsListTmp = studentList
        if (studentsListTmp.value == null) studentsListTmp.value = StudentsList()
        studentsListTmp.value!!.items.add(student)
        studentList.postValue(studentsListTmp.value)
    }

    fun getPostition(student: Student): Int = studentList.value?.items?.indexOfFirst {
        it.id == student.id } ?: -1

    fun updateStudent(student: Student) {
        var studentsListTmp = studentList
        val position = getPostition(student)
        if (studentsListTmp.value == null || position < 0) addStudent(student)
        else {
            studentsListTmp.value!!.items[position]=student
            studentList.postValue(studentsListTmp.value)
        }
    }

    fun deleteStudent(student: Student) {
        var studentsListTmp = studentList
        if (studentsListTmp.value!!.items.remove(student)) {
            studentsListTmp.postValue(studentsListTmp.value)
        }
    }

    fun newStudent() {
        student.postValue(Student())
    }
}