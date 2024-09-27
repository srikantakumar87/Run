package com.sri.core.domain.runs

import com.sri.core.domain.location.Location
import java.time.ZonedDateTime
import kotlin.time.Duration
import kotlin.time.DurationUnit

data class Run(
    val id: String?,
    val duration: Duration,
    val dateTimeUtc: ZonedDateTime,
    val distanceMeters: Int,
    val location: Location,
    val maxSpeed: Double,
    val totalElevationMeters: Int,
    val mapPictureUrl: String?
){
    val avgSpeedKmh: Double
        get() = distanceMeters / 1000.0 / (duration.toDouble(DurationUnit.HOURS))
}

