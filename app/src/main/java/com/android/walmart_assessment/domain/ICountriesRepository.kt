package com.android.walmart_assessment.domain

import com.android.walmart_assessment.data.Country

interface ICountriesRepository {

    suspend fun getCountriesList(): Result<List<Country>>
}