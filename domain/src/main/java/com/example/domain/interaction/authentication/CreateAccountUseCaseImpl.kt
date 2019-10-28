package com.example.domain.interaction.authentication

import com.example.domain.persistence.EmailPasswordAuthInterface

class CreateAccountUseCaseImpl(private val auth: EmailPasswordAuthInterface) :
    CreateAccountUseCase {

    override fun invoke(
        email: String,
        password: String,
        checkIfTaskSuccess: (Boolean) -> Unit
    ) {
        auth.createAccount(email, password, checkIfTaskSuccess)
    }

}