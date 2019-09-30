package hr.damirjurkovic.dolaznost.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey (autoGenerate = true)
    var courseDbId: Int? = null,
    val courseName: String,
    var numLectures: Int,
    var numExercises: Int,
    var numLaboratory: Int,
    val attendancePercent: Int,
    val attendanceNum: Double,
    var leftHoursQuota: Double,
    var wentHours: Double,
    var leftHoursAll: Double,
    var alarmState: Double
)