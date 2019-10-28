package com.example.domain.interaction.authentication

import com.example.domain.persistence.EmailPasswordAuthInterface

class SignOutUseCaseImpl(private val auth: EmailPasswordAuthInterface) :
    SignOutUseCase {
    override fun invoke() = auth.signOut()
}
