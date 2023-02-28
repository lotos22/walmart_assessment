package com.android.walmart_assessment.utils

import com.android.walmart_assessment.data.CountriesApi
import com.android.walmart_assessment.data.CountriesNetworkDataSource
import com.android.walmart_assessment.data.CountriesRepository
import com.android.walmart_assessment.domain.FetchCountriesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class Di {

    companion object {
        fun getCountriesVMFactory() = CountriesVMFactory(
            getFetchCountriesUseCase()
        )

        private fun getFetchCountriesUseCase() = FetchCountriesUseCase(
            getCountriesRepository()
        )

        private fun getCountriesRepository() = CountriesRepository(
            getCountriesDataSource()
        )

        private fun getCountriesDataSource() = CountriesNetworkDataSource(
            countriesApi
        )

        private const val baseUrl = "https://gist.githubusercontent.com/"
        private fun getApiService() = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val countriesApi: CountriesApi by lazy {
            getApiService().create(CountriesApi::class.java)
        }
    }
}