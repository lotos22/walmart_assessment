package com.android.walmart_assessment.data

import retrofit2.Response
import retrofit2.Retrofit
import java.lang.Exception

class CountriesNetworkDataSource(private val apiService: CountriesApi) {

    suspend fun getCountries(): Response<List<Country>> {
        return apiService.getListCountries()
    }

}