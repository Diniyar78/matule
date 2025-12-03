package com.sirazovdiniyar.matule.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val emailVisibility: Boolean,
    val firstname: String,
    val id: String,
    val lastname: String,
    val secondname: String,
    val updated: String,
    val verified: Boolean,
    val dateBirthday: String,
    val gender: String,
    val email: String,
)
