package com.example.domain.interaction.authentication

import com.example.domain.persistence.EmailPasswordAuthInterface

class SignInUseCaseImpl(private val auth: EmailPasswordAuthInterface) :
    SignInUseCase {
    override fun invoke(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit) {
        auth.signIn(email, password, checkIfTaskSuccess)
    }
}