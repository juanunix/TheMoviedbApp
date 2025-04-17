package com.juansanz.data

import com.juansanz.data.PermissionChecker.Permission.COARSE_LOCATION
import com.juansanz.data.datasource.LocationDataSource
import javax.inject.Inject

class RegionRepository
    @Inject
    constructor(
        private val locationDataSource: LocationDataSource,
        private val permissionChecker: PermissionChecker,
    ) {
        companion object {
            const val DEFAULT_REGION = "US"
        }

        suspend fun findLastRegion(): String =
            if (permissionChecker.check(COARSE_LOCATION)) {
                locationDataSource.findLastRegion() ?: DEFAULT_REGION
            } else {
                DEFAULT_REGION
            }
    }
