package com.juansanz.data.datasource

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}
