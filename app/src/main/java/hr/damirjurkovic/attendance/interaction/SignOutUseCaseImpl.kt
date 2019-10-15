package hr.damirjurkovic.attendance.interaction

import com.google.firebase.auth.FirebaseAuth

class SignOutUseCaseImpl(private val auth: FirebaseAuth): SignOutUseCase {
    override fun invoke() = auth.signOut()
}