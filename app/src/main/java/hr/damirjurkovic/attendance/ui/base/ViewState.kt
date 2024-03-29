package hr.damirjurkovic.attendance.ui.base

sealed class ViewState<out T : Any>
class Success<out T : Any>(val data: T) : ViewState<T>()
class Loading<out T : Any> : ViewState<T>()



