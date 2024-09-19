package com.sri.core.data.networking

import kotlinx.serialization.Serializable

@Serializable
class AccessTokenRequest(
    val refreshToken: String,
    val userId: String

)