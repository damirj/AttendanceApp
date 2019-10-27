package hr.damirjurkovic.data.common

import com.example.domain.persistence.EmailPasswordAuthInterface
import com.google.firebase.auth.FirebaseAuth

class EmailPasswordAuth(private val auth: FirebaseAuth) : EmailPasswordAuthInterface {

    override fun createAccount(
        email: String,
        password: String,
        checkIfTaskSuccess: (Boolean) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                checkIfTaskSuccess(true)
            } else {
                checkIfTaskSuccess(false)
            }
        }
    }

    override fun signIn(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                checkIfTaskSuccess(true)
            } else {
                checkIfTaskSuccess(false)
            }
        }
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun checkIfLoggedIn() = auth.currentUser != null

    override fun validateForm(email: String, password: String) =
        !(email.isEmpty() || password.isEmpty())
}