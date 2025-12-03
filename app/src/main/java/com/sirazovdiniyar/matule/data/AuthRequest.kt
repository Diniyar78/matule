package com.sirazovdiniyar.matule.data

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val identity: String,
    val password: String
)
