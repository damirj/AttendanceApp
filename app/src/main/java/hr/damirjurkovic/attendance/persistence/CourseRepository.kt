package hr.damirjurkovic.attendance.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import hr.damirjurkovic.attendance.model.Course
import java.util.concurrent.TimeUnit

class CourseRepository(
    private val firebaseDatabase: FirebaseDatabase,
    private val auth: FirebaseAuth
) :
    RepositoryInterface {


    override fun getAllCourses(): LiveData<MutableList<Course>> {
        firebaseDatabase.setPersistenceEnabled(true)
        val uid = auth.currentUser?.uid ?: 0
        val myRef =
            firebaseDatabase.getReference("users/$uid/courses") //TODO izbaciti string u konstante
        val courses = MutableLiveData<MutableList<Course>>()
        val localCourses = mutableListOf<Course>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                localCourses.clear()
                for (data in dataSnapshot.children) {
                    data?.getValue(Course::class.java)?.let {
                        localCourses.add(it)
                    }
                }
                courses.value = localCourses
            }
        })
        return courses
    }

    override fun insertCourse(course: Course) {
        val myRef = firebaseDatabase.getReference("users")
        val uid = auth.currentUser?.uid ?: "0"
        val timeStamp = (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())).toString()
        course.courseDbId = timeStamp.toInt()

        myRef.child(uid).child("courses").child(timeStamp).setValue(course)
    }

    override fun updateCourse(course: Course) {
        val uid = auth.currentUser?.uid ?: 0
        val myRef = firebaseDatabase.getReference("users/$uid/courses/${course.courseDbId}")

        myRef.setValue(course)
    }

    override fun deleteCourse(course: Course) {
        val myRef = firebaseDatabase.getReference("users")
        val uid = auth.currentUser?.uid ?: "0"

        myRef.child(uid).child("courses").child(course.courseDbId.toString()).setValue(null)
    }

    override fun deleteAllCourses() {
        firebaseDatabase.reference.setValue(null)
    }

    override fun getCourse(courseId: Int): LiveData<Course> {
        val uid = auth.currentUser?.uid ?: 0
        val myRef = firebaseDatabase.getReference("users/$uid/courses/$courseId")
        val course = MutableLiveData<Course>()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                course.value = dataSnapshot.getValue(Course::class.java)
            }
        })
        return course
    }
}