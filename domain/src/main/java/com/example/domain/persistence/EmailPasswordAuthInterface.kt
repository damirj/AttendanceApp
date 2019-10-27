package com.example.domain.persistence

interface EmailPasswordAuthInterface {

    fun createAccount(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit)

    fun signIn(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit)

    fun signOut()

    fun checkIfLoggedIn(): Boolean

    fun validateForm(email: String, password: String): Boolean
}