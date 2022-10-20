package com.example.appstudents

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appstudents.data.Student

class StudentListFragment : Fragment() {
    private lateinit var studentListViewModel: StudentListViewModel
    private lateinit var studentListResyclerView: RecyclerView

    companion object {
        private var INSTANCE: StudentListFragment? = null

        fun getInstance():StudentListFragment {
            if (INSTANCE == null) {
                INSTANCE = StudentListFragment()
            }
            return  INSTANCE?: throw IllegalStateException("Отображение списка не создано!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutView = inflater.inflate(R.layout.list_of_student,container, false)
        studentListResyclerView = layoutView.findViewById(R.id.rvList)
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentListViewModel = ViewModelProvider(this).get(StudentListViewModel::class.java)
    }

    private inner class StudentListAdapter(private val orderItems: List<Student>)
        :RecyclerView.Adapter<StudentHolder>() {
        override  fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): StudentHolder {
            val view = layoutInflater.inflate(R.layout.students_list_element, parent, false)
            return StudentHolder(view)
        }

        override fun getItemCount(): Int = orderItems.size

        override  fun onBindViewHolder(holder: StudentHolder, position: Int) {
            holder.bind(orderItems[position])
        }
    }

    private inner class StudentHolder(view: View)
        :RecyclerView.ViewHolder(view) {

        private  lateinit var student: Student
        private var fioTextView: TextView = itemView.findViewById(R.id.tvID)
        private var ageTextView: TextView = itemView.findViewById(R.id.tvAge)
        private var groupTextView: TextView = itemView.findViewById(R.id.tvGroup)

        fun bind(student: Student) {
            this.student = student
            fioTextView.text = "${student.lastName} ${student.firstName} ${student.middleName}"
            groupTextView.text = student.group
            ageTextView.text = student.age.toString()
        }

    }
}