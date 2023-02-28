package com.android.walmart_assessment.data

import com.android.walmart_assessment.domain.ICountriesRepository

class CountriesRepository(
    private val countriesNetworkDataSource: CountriesNetworkDataSource
) : ICountriesRepository {

    override suspend fun getCountriesList(): Result<List<Country>> {
        //Get the data from api, if fails from database
        //Map the data and exceptions
        return try {
            val response = countriesNetworkDataSource.getCountries()
            Result.success(response.body()!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}