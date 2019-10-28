package com.example.domain.interaction.authentication

interface ValidateFormUseCase {

    operator fun invoke(email: String, password: String): Boolean
}