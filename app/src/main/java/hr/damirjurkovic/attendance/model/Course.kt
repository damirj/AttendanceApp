package hr.damirjurkovic.attendance.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    var courseDbId: Int? = null,
    val courseName: String = "",
    var numLectures: Int = 0,
    var numExercises: Int = 0,
    var numLaboratory: Int = 0,
    val attendancePercent: Int = 0,
    val attendanceNum: Double = 0.0,
    var leftHoursQuota: Double = 0.0,
    var wentHours: Double = 0.0,
    var leftHoursAll: Double = 0.0,
    var alarmState: Double = 0.0
) {
    // constructor() : this(0, "",)
}