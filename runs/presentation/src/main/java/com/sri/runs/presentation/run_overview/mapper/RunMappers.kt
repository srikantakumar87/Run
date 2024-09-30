package com.sri.runs.presentation.run_overview.mapper

import com.sri.core.domain.runs.Run
import com.sri.core.presentation.ui.formatted
import com.sri.core.presentation.ui.toFormattedKm
import com.sri.core.presentation.ui.toFormattedKmh
import com.sri.core.presentation.ui.toFormattedMeters
import com.sri.core.presentation.ui.toFormattedPace
import com.sri.runs.presentation.run_overview.model.RunUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Run.toRunUi(): RunUi {
    val dateTimeInLocalTime = dateTimeUtc
        .withZoneSameInstant(ZoneId.systemDefault())
    val formatedDateTime = DateTimeFormatter.ofPattern("MMM dd, yyyy - hh:mma")
        .format(dateTimeInLocalTime)
    val distanceInKm = distanceMeters / 1000.0
    return RunUi(
        id = id!!,
        duration = duration.formatted(),
        dateTime = formatedDateTime,
        distance = distanceInKm.toFormattedKm(),
        avgSpeed = avgSpeedKmh.toFormattedKmh(),
        maxSpeed = maxSpeedKmh.toFormattedKmh(),
        pace = duration.toFormattedPace(distanceInKm),
        totalElevation = totalElevationMeters.toFormattedMeters(),
        mapPictureUrl = mapPictureUrl
    )

}