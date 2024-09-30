package com.sri.runs.network

import kotlinx.serialization.Serializable

@Serializable
data class RunDto(
    val id: String,
    val durationMillis: Long,
    val dateTimeUtc: String,
    val distanceMeters: Int,
    val lat: Double,
    val long: Double,
    val avgSpeedKmh: Double,
    val maxSpeedKmh: Double,
    val totalElevationMeters: Int,
    val mapPictureUrl: String?
)
