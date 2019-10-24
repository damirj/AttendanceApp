package hr.damirjurkovic.attendance.ui.login.view

sealed class EmailPasswordEffect
object Success : EmailPasswordEffect()
object Fail : EmailPasswordEffect()
object LoggedIn : EmailPasswordEffect()

