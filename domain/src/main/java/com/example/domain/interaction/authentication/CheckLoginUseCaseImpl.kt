package com.example.domain.interaction.authentication

import com.example.domain.persistence.EmailPasswordAuthInterface

class CheckLoginUseCaseImpl(private val auth: EmailPasswordAuthInterface) :
    CheckLoginUseCase {

    override fun invoke() = auth.checkIfLoggedIn()

}