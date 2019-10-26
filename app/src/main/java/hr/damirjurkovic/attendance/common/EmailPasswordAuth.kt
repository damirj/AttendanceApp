package hr.damirjurkovic.attendance.common

import com.google.firebase.auth.FirebaseAuth

class EmailPasswordAuth(private val auth: FirebaseAuth) {

    fun createAccount(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                checkIfTaskSuccess(true)
            } else {
                checkIfTaskSuccess(false)
            }
        }
    }

    fun signIn(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                checkIfTaskSuccess(true)
            } else {
                checkIfTaskSuccess(false)
            }
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun checkIfLoggedIn() = auth.currentUser != null

    fun validateForm(email: String, password: String) = !(email.isEmpty() || password.isEmpty())
}