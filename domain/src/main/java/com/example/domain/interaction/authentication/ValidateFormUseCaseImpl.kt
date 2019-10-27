package com.example.domain.interaction.authentication

import com.example.domain.persistence.EmailPasswordAuthInterface

class ValidateFormUseCaseImpl(private val auth: EmailPasswordAuthInterface) :
    ValidateFormUseCase {
    override fun invoke(email: String, password: String) = auth.validateForm(email, password)
}