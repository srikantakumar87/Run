package com.sri.runs.location

import android.location.Location
import com.sri.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.sri.core.domain.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )

}