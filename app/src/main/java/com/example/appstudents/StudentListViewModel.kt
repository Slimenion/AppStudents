package com.example.appstudents

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.appstudents.MyConstants.TAG
import com.example.appstudents.data.StudentsList
import com.example.appstudents.repository.StudentRepository

class StudentListViewModel : ViewModel() {
    var studentsList: MutableLiveData<StudentsList> = MutableLiveData()
    private var observer: Observer<StudentsList> = Observer<StudentsList>
    {newList ->
        newList?.let {
            Log.d(TAG, "Получен список  StudentListVuewModel от StudentsRepository")
            studentsList.postValue(newList)
        }
    }

    init {
        StudentRepository.getInstance().studentList.observeForever(observer)
        Log.d(TAG, "Подписалась StudentListViewModel к StudentsRepository")
    }
}