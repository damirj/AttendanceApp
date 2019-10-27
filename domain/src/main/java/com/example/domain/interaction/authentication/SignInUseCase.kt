package com.example.domain.interaction.authentication

interface SignInUseCase {

    operator fun invoke(email: String, password: String, checkIfTaskSuccess: (Boolean) -> Unit)
}