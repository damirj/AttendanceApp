package com.example.domain.interaction.authentication

interface CreateAccountUseCase {

    operator fun invoke(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit)
}