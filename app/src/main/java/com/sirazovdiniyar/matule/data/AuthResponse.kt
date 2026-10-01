package com.sirazovdiniyar.matule.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val record: User,
    val token: String
)
